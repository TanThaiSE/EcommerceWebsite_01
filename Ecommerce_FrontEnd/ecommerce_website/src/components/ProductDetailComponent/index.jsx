import React, { useEffect, useState } from 'react'
import { apiCart, apiProduct } from '../../api';
import './index.css';
const ProductDetailComponent = ({ idProduct }) => {
  const [productDetail, setProductDetail] = useState({});
  const [attributeProduct, setAttributeProduct] = useState({
    productId: idProduct,
    colorId: null,
    sizeId: null,
    quantity: 1,
  });
  const [fullFill, setFullFill] = useState(null);
  const [colorBtnChoose, setColorBtnChoose] = useState({
    activeColor: '',
    activeSize: '',
  });
  const fetchDetailProduct = async (idProduct) => {
    await apiProduct.fetchGetDetailProduct(idProduct)
      .then((res) => {
        setProductDetail(res.data);
      })
      .catch((err) => { console.log('fetchDetailProduct failed', err) });
  };

  const fetchAddToCart = async (product) => {
    await apiCart.fetchAddCart(product)
      .then(async(res) => {
        if (res.data.code === "201") {
          //handle add product to cart success
          console.log('handle add product to cart success',res.data);
        }
        else if (res.data.code === "302") {
          //handle update quantity product in cart
          let dataUpdate={
            ...res.data.dataResponse,
            quantity:Number(res.data.dataResponse.quantity)+Number(product.quantity)
          }
          await apiCart.fetchUpdateCart(res.data.dataResponse.id,dataUpdate)
          .then((res1)=>{
            //handle update success
            console.log('res1',res1.data);
          })
          .catch((err1)=>{console.log('fetchUpdateCart failed',err1);})
        }

      })
      .catch((err) => { console.log('fectchAddToCart failed', err) });
  }

  const getAttributeColorProduct = (event) => {
    let { value, id } = event.target;
    if (colorBtnChoose.activeColor === id) {
      setColorBtnChoose({ ...colorBtnChoose, activeColor: '' });
      setAttributeProduct({
        ...attributeProduct,
        colorId: null,
      });
    }
    else {
      setColorBtnChoose({ ...colorBtnChoose, activeColor: id });
      setAttributeProduct({
        ...attributeProduct,
        colorId: value,
      });
    }
  };

  const getAttributeSizeProduct = (event) => {
    let { value, id } = event.target;
    if (colorBtnChoose.activeSize === id) {
      setColorBtnChoose({ ...colorBtnChoose, activeSize: '' });
      setAttributeProduct({
        ...attributeProduct,
        sizeId: null,
      });
    }
    else {
      setColorBtnChoose({ ...colorBtnChoose, activeSize: id });
      setAttributeProduct({
        ...attributeProduct,
        sizeId: value,
      });
    }
  };

  const setAmountProduct = (type) => {
    if (type === 1) {
      if (attributeProduct.quantity <= 1) {
        setAttributeProduct({
          ...attributeProduct,
          quantity: 1
        })
      }
      else {
        setAttributeProduct({
          ...attributeProduct,
          quantity: attributeProduct.quantity - 1
        })
      }
    }
    else if (type === 2) {
      setAttributeProduct({
        ...attributeProduct,
        quantity: attributeProduct.quantity + 1
      })
    }
  };

  const handleInputmountProduct = (event) => {
    let { value } = event.target;
    setAttributeProduct({
      ...attributeProduct,
      quantity: value
    })
  };

  const handleCheckInputmountProduct = () => {
    if (attributeProduct.quantity === '' || Number(attributeProduct.quantity) < 1) {
      setAttributeProduct({
        ...attributeProduct,
        quantity: 1
      })
    }
  };

  const addProductToCart = () => {
    if (attributeProduct.colorId === null) {
      setFullFill(1);
      return;
    }
    if (attributeProduct.sizeId === null) {
      setFullFill(1);
      return;
    }
    setFullFill(null);
    fetchAddToCart({
      ...attributeProduct,
      price: productDetail?.detailProduct?.price,
    });
  };

  const displayDetailProduct = (productDetail) => {
    if (Object.keys(productDetail).length !== 0) {
      return (<>
        <div className="row">
          <div className="col-md-5">
            <img src="" alt="" />
            <div className="row">
              slide image
            </div>
          </div>
          <div className="col-md-6">
            <div className="head-product">
              <p className='head-product-name-product'>{productDetail?.detailProduct?.nameProduct}</p>
              <div className='d-flex'>
                <div>
                  <span>{productDetail?.detailProduct?.rate}</span>
                  <span>so sao</span>
                </div>
                <div>
                  Đã bán: {productDetail?.detailProduct?.number_buy}
                </div>
              </div>
            </div>

            <div className="price-product">
              {productDetail?.detailProduct?.price}
            </div>

            <div className={fullFill === null ? ('') : ('hightlight-attribute')}>
              <div className="character-product">
                {productDetail?.colorProduct?.length > 0 ? (<>
                  <div className='d-flex'>
                    <p className='color-name'>Màu</p>
                    <div className='d-flex'>
                      {productDetail?.colorProduct?.map((item, index) => {
                        return (<button className={`btn-color-product ${colorBtnChoose.activeColor === item.id ? ('bg-color-choose') : ('')}`} key={`colorProduct-${index}`} name='colorProduct' id={item.id} onClick={getAttributeColorProduct} value={item.id}>{item.name}</button>);
                      })}

                    </div>
                  </div>

                </>) : (<></>)}

                {productDetail?.sizeProduct?.length > 0 ? (<>
                  <div className='d-flex'>
                    <p className='color-name'>Size</p>
                    <div className='d-flex'>
                      {productDetail?.sizeProduct?.map((item, index) => {
                        return (<button className={`btn-color-product ${colorBtnChoose.activeSize === item.id ? ('bg-color-choose') : ('')}`} key={`sizeProduct-${index}`} name='sizeProduct' id={item.id} onClick={getAttributeSizeProduct} value={item.id}>{item.name}</button>);
                      })}

                    </div>
                  </div>

                </>) : (<></>)}
                <div className='d-flex'>
                  <p className='color-name'>Số lượng</p>
                  <div className='d-flex'>
                    <button className='btn-color-product ammount-product' onClick={() => { setAmountProduct(1) }}>-</button>
                    <input type="number" value={attributeProduct.quantity} className='amountProduct' onChange={handleInputmountProduct} onBlur={handleCheckInputmountProduct} />
                    <button className='btn-color-product ammount-product' onClick={() => { setAmountProduct(2) }}>+</button>
                  </div>
                </div>
                <p className={fullFill === null ? ('choose-full-attribute-close') : ('choose-full-attribute-open')}>Vui lòng chọn Phân loại hàng</p>

              </div>

            </div>


            <div className="buy-product">
              <button onClick={() => { addProductToCart() }}>THÊM VÀO GIỎ HÀNG</button>
            </div>



          </div>
        </div>

        <div className='describe-product'>
          <div className="detail-product">
            <p>CHI TIẾT SẢN PHẨM</p>
            {/* danger */}
            {productDetail?.detailProduct?.detail}
          </div>
          <div className="description-product">
            {/* danger */}
            {productDetail?.detailProduct?.descriptionProduct}
          </div>
        </div>

        <div className='review-product'>
          UPDATING!!
        </div>
      </>)
    }
    else {
      return (<></>);
    }
  }

  useEffect(() => {
    fetchDetailProduct(idProduct);
  }, []);

  return (

    <>
      <div className="container">
        {displayDetailProduct(productDetail)}

      </div>
    </>
  )
}

export default ProductDetailComponent;