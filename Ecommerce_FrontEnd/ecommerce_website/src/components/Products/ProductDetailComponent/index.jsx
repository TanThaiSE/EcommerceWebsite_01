import React, { useEffect, useState } from 'react'
import { Link, useNavigate } from 'react-router-dom';
import { apiCart, apiProduct, apiRating } from '../../../api';
import './index.css';
import StarRatings from 'react-star-ratings';
import Slider from "react-slick";
import "slick-carousel/slick/slick.css";
import "slick-carousel/slick/slick-theme.css";
import moment from 'moment';
import ModalSuccess from '../../Modals/ModalSuccess';
import { getLogin } from '../../../utils/cookieStorage';
const ProductDetailComponent = ({ idProduct }) => {
  const navigate = useNavigate();
  const [productDetail, setProductDetail] = useState({});
  const [listComment, setListComment] = useState([]);
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
  const [displayImage, setDisplayImage] = useState(null);
  const [showModal, setShowModal] = useState(false);
  const settings = {
    infinite: true,
    slidesToShow: 3,
    slidesToScroll: 3
  };
  const fetchDetailProduct = async (idProduct) => {
    await apiProduct.fetchGetDetailProduct(idProduct)
      .then((res) => {
        setDisplayImage(res.data.imageProduct[0].name_image);
        setProductDetail(res.data);
      })
      .catch((err) => { console.log('fetchDetailProduct failed', err) });
  };

  const fetchGetListComment = async (idProduct) => {
    apiRating.fetchGetRatingAndComment(idProduct).then((res) => { setListComment(res.data) }).catch((err) => console.log(err))
  }

  const fetchAddToCart = async (product) => {
    await apiCart.fetchAddCart(product)
      .then(async (res) => {
        if (res.data.code === "201") { }
        else if (res.data.code === "302") {
          let dataUpdate = {
            ...res.data.dataResponse,
            quantity: Number(res.data.dataResponse.quantity) + Number(product.quantity)
          }
          await apiCart.fetchUpdateCart(res.data.dataResponse.id, dataUpdate)
            .then((res1) => {
            })
            .catch((err1) => { console.log('fetchUpdateCart failed', err1); })
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

  const handleMouseImage = (e) => {
    setDisplayImage(e.target.src);
  }
  const addProductToCart = () => {
    if (getLogin.getToken() === undefined) {
      navigate(`/login`);
    }
    if (attributeProduct.colorId === null) {
      setFullFill(1);
      return;
    }
    if (attributeProduct.sizeId === null) {
      setFullFill(1);
      return;
    }

    setFullFill(null);
    handleShowModal();
    fetchAddToCart({
      ...attributeProduct,
      price: productDetail?.detailProduct?.price,
      accountId:getLogin.getId()
    });
    setTimeout(() => { handleCloseModal(); }, 2000);
  };
  const handleShowModal = () => { setShowModal(true); }
  const handleCloseModal = () => { setShowModal(false); }
  const displayDetailProduct = (productDetail) => {
    if (Object.keys(productDetail).length !== 0) {
      return (<>
        <div className="row mt-5">
          <div className="col-md-5">
            <img src={displayImage} alt={productDetail?.detailProduct?.nameProduct} style={{ width: '100%' }} />
            <div className="row ml-3 mt-2">
              <Slider {...settings}>
                {productDetail.imageProduct.map((item, index) => {
                  return (
                    <div key={`imageSlide-${index}`}>
                      <img src={item.name_image} alt={productDetail?.detailProduct?.nameProduct} style={{ width: '100px', height: '100px' }} onMouseEnter={handleMouseImage} />
                    </div>
                  )
                })}
              </Slider>
            </div>
          </div>
          <div className="col-md-1"></div>
          <div className="col-md-6">
            <div className="head-product">
              <p className='head-product-name-product'>{productDetail?.detailProduct?.nameProduct}</p>
              <div className='d-flex' >
                <div style={{ marginRight: '67px' }}>
                  <span style={{ marginRight: '10px' }}>{productDetail?.detailProduct?.rate}</span>
                  <StarRatings
                    rating={productDetail?.detailProduct?.rate ? (productDetail?.detailProduct?.rate) : (0)}
                    starRatedColor='#ffce3d'
                    numberOfStars={5}
                    starDimension="16px"
                    starSpacing="2px"
                    size={50}
                  />
                </div>
                <div>
                  Đã bán: {productDetail?.detailProduct?.number_buy}
                </div>
              </div>
            </div>

            <div className="price-product">
              {productDetail?.detailProduct?.price}
            </div>
            {productDetail?.detailProduct?.statusProduct === 0 ? (
              <>
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
                  <button onClick={() => { addProductToCart() }}>Thêm Vào Giỏ Hàng</button>
                </div>
              </>
            ) : (<><p>Hết hàng</p></>)
            }
          </div>
        </div>

        <div className='describe-product'>
          <div className="detail-product">
            <p className="detail-product-title">CHI TIẾT SẢN PHẨM</p>
            <div dangerouslySetInnerHTML={{ __html: productDetail?.detailProduct?.detail }} className='detail-product-content' />
          </div>
          <div className="description-product">
            <p className="description-product-title">MÔ TẢ SẢN PHẨM</p>
            <div dangerouslySetInnerHTML={{ __html: productDetail?.detailProduct?.descriptionProduct }} className='description-product-content' />
          </div>
          <div className="review-product">
            <p className="review-product-title">ĐÁNH GIÁ SẢN PHẨM</p>
          </div>
        </div>
      </>)
    }
    else {
      return (<></>);
    }
  }

  const displayRating = (listComment) => {
    if (listComment.length > 0) {
      return listComment.map((item, index) => {
        return (
          <div key={`rating-${index}`}>
            <div className='d-flex'>
              <div>
                <img src={`https://i.pravatar.cc/69`} alt="" className='avatar-customer' />
              </div>
              <div>
                <span>{item?.name ? (item?.name) : ('Unknown')}</span>
                <StarRatings
                  rating={item?.pointRate}
                  starRatedColor='#ffce3d'
                  numberOfStars={5}
                  starDimension="16px"
                  starSpacing="2px"
                />
                <p>{moment(item.ratingDate).format("DD/MM/YYYY HH:MM:SS")}</p>
                <p>{item.comments}</p>

              </div>

            </div>
            <hr />
          </div>
        )
      })
    }
    else {
      return (<></>);
    }

  }

  useEffect(() => {
    fetchDetailProduct(idProduct);
    fetchGetListComment(idProduct);
  }, []);

  return (

    <>
      <div className="container">
        {displayDetailProduct(productDetail)}
        {listComment.length > 0 ? (displayRating(listComment)) : (<></>)}
      </div>
      <ModalSuccess headers={"Success"} title={'Đã thêm vào giỏ hàng'} handleCloseModal={handleCloseModal} showModal={showModal} />

    </>
  )
}

export default ProductDetailComponent;