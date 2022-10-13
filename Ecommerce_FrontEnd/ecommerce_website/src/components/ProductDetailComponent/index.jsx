import React, { useEffect, useState } from 'react'
import { apiCart, apiProduct } from '../../api';
import './index.css';
import { v4 as uuid } from "uuid";
import { getLogin } from '../../utils/localStorage';
const ProductDetailComponent = ({ idProduct }) => {
  const [detailProduct, setDetailProduct] = useState({});
  const [attributeProduct, setAttributeProduct] = useState({
    idProduct: idProduct,
    colorProduct: null,
    sizeProduct: null,
    amountProduct: 1,
  });
  const [checkIsHaveColorProduct, setCheckIsHaveColorProduct] = useState(0);
  const [fullFill, setFullFill] = useState(null);
  const [colorBtnChoose, setColorBtnChoose] = useState({
    activeColor: '',
    activeSize: '',
  });
  const fetchDetailProduct = async (idProduct) => {
    apiProduct.fetchGetDetailProduct(idProduct)
      .then((res) => {
        setCheckIsHaveColorProduct(res.data.colorProduct.length);
        setDetailProduct(res.data);
      })
      .catch((err) => { console.log('fetchDetailProduct failed', err) });
  };

  const fetchAddToCart=async(product)=> {
    // apiCart.fetchAddCart(product)
    // .then((res)=>{
    //   if(res.data.type===1){
    //     console.log('sanr pham da them vao gio hang');
    //   }
    //   else if(res.data.type===-1){
    //     console.log('faled anr pham da them vao gio hang');
    //   }
    // })
    // .catch((err) => { console.log('fectchAddToCart failed', err) });
    console.log('fectchAddToCart',product);
  }

  const getAttributeColorProduct = (event) => {
    let {value, id } = event.target;
    if (colorBtnChoose.activeColor === id) {
      setColorBtnChoose({ ...colorBtnChoose, activeColor: '' });
      setAttributeProduct({
        ...attributeProduct,
        colorProduct: null,
      });
    }
    else {
      setColorBtnChoose({ ...colorBtnChoose, activeColor: id });
      setAttributeProduct({
        ...attributeProduct,
        colorProduct: value,
      });
    }
  };

  const getAttributeSizeProduct = (event) => {
    let {value, id } = event.target;
    if (colorBtnChoose.activeSize === id) {
      setColorBtnChoose({ ...colorBtnChoose, activeSize: '' });
      setAttributeProduct({
        ...attributeProduct,
        sizeProduct: null,
      });
    }
    else {
      setColorBtnChoose({ ...colorBtnChoose, activeSize: id });
      setAttributeProduct({
        ...attributeProduct,
        sizeProduct: value,
      });
    }
  };

  const setAmountProduct=(type)=>{
    if(type===1){
      if(attributeProduct.amountProduct<=1){
        setAttributeProduct({
          ...attributeProduct,
          amountProduct:1
        })
      }
      else{
        setAttributeProduct({
          ...attributeProduct,
          amountProduct:attributeProduct.amountProduct-1
        })
      }
    }
    else if(type===2){
      setAttributeProduct({
        ...attributeProduct,
        amountProduct:attributeProduct.amountProduct+1
      })
    }
  };

  const handleInputmountProduct=(event)=>{
    let {value } = event.target;
    setAttributeProduct({
      ...attributeProduct,
      amountProduct:value
    })
  };

  const handleCheckInputmountProduct=()=>{
    if(attributeProduct.amountProduct===''||Number(attributeProduct.amountProduct)<1){
      setAttributeProduct({
        ...attributeProduct,
        amountProduct:1
      })
    }
  };

  const addProductToCart = () => {
    if (checkIsHaveColorProduct) {
      if (attributeProduct.colorProduct === null) {
        setFullFill(1);
        return;
      }
    }
    if (attributeProduct.sizeProduct === null) {
      setFullFill(1);
      return;
    }
    setFullFill(null);
    fetchAddToCart({
      ...attributeProduct,
      id:uuid(),
      price:detailProduct.price,
      accountId:getLogin.getUserId(),
      statusCart:0
    });
  };

  const displayDetailProduct = (detailProduct) => {
    if (Object.keys(detailProduct).length !== 0) {
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
              <p className='head-product-name-product'>{detailProduct.nameProduct}</p>
              <div className='d-flex'>
                <div>
                  <span>{detailProduct.rate}</span>
                  <span>so sao</span>
                </div>
                <div>
                  Đã bán: {detailProduct.number_buy}
                </div>
              </div>
            </div>

            <div className="price-product">
              {detailProduct.price}
            </div>

            <div className={fullFill === null ? ('') : ('hightlight-attribute')}>
              <div className="character-product">
                {detailProduct.colorProduct.length > 0 ? (<>
                  <div className='d-flex'>
                    <p className='color-name'>Màu</p>
                    <div className='d-flex'>
                      {detailProduct.colorProduct.map((item, index) => {
                        return (<button className={`btn-color-product ${colorBtnChoose.activeColor === item.id ? ('bg-color-choose') : ('')}`} key={`colorProduct-${index}`} name='colorProduct' id={item.id} onClick={getAttributeColorProduct} value={item.id}>{item.name}</button>);
                      })}

                    </div>
                  </div>

                </>) : (<></>)}

                {detailProduct.sizeProduct.length > 0 ? (<>
                  <div className='d-flex'>
                    <p className='color-name'>Size</p>
                    <div className='d-flex'>
                      {detailProduct.sizeProduct.map((item, index) => {
                        return (<button className={`btn-color-product ${colorBtnChoose.activeSize === item.id ? ('bg-color-choose') : ('')}`} key={`sizeProduct-${index}`} name='sizeProduct' id={item.id} onClick={getAttributeSizeProduct} value={item.id}>{item.name}</button>);
                      })}

                    </div>
                  </div>

                </>) : (<></>)}
                <div className='d-flex'>
                  <p className='color-name'>Số lượng</p>
                  <div className='d-flex'>
                    <button className='btn-color-product ammount-product' onClick={()=>{setAmountProduct(1)}}>-</button>
                    <input type="number" value={attributeProduct.amountProduct} className='amountProduct' onChange={handleInputmountProduct} onBlur={handleCheckInputmountProduct}/>
                    <button className='btn-color-product ammount-product' onClick={()=>{setAmountProduct(2)}}>+</button>
                  </div>
                </div>
                {/* */}
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
            {detailProduct.detail}
          </div>
          <div className="description-product">
            {/* danger */}
            {detailProduct.descriptionProduct}
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
        {displayDetailProduct(detailProduct)}

      </div>
    </>
  )
}

export default ProductDetailComponent;