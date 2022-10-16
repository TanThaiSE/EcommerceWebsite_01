import React, { useEffect, useState } from 'react';
// import { apiCart, apiProduct } from '../../api';
import { apiCart } from '../../api';
import './index.css';
const CartComponent = () => {
  const [listProducts, setListProducts] = useState([]);
  const [isEmptyCart, setIsEmptyCart] = useState(false);
  const fetchGetProductsInCart = async () => {
    await apiCart.fetchGetCart()
      .then((res) => {
        setListProducts(res.data);
      })
      .catch((err) => {
        if (err.response.data.code === "404") {
          //not found data
          setIsEmptyCart(true);
        }
      })
  }

  const displayProducts = (listProducts) => {
    if (listProducts.length > 0) {
      return listProducts.map((item, index) => {
        return (
          <div className="row" key={`listProducts-${index}`}>
            <div className="col-md-1">
              <input type='checkbox' />
            </div>
            <div className="col-md-5">
              <div className="row">
                <div className="col-md-1">
                  <img src={item.imgName} alt={item.productName}/>
                </div>
                <div className="col-md-2">
                  <p>{item.productName}</p>
                </div>
                <div className="col-md-2">
                  <p>{item.sizeName},{item.colorName} </p>
                </div>
              </div>
            </div>
            <div className="col-md-2">
              <p>{item.price}</p>
            </div>
            <div className="col-md-2">
              <p>{item.quantity}</p>
            </div>
            <div className="col-md-1">
              <p>{Number(item.price)*Number(item.quantity)}</p>
            </div>
            <div className="col-md-1">
              <p>Xóa</p>
            </div>
          </div>
        );
      });
    }
    else {
      return (<></>);
    }
  }
  useEffect(() => {
    fetchGetProductsInCart();
  }, []);


  return (
    <div className='container'>
      <div className="row">
        <div className="col-md-1">
          <input type='checkbox' />
        </div>
        <div className="col-md-5">
          <p>Sản phẩm</p>
        </div>
        <div className="col-md-2">
          <p>Đơn giá</p>
        </div>
        <div className="col-md-2">
          <p>Số lượng</p>
        </div>
        <div className="col-md-1">
          <p>Số tiền</p>
        </div>
        <div className="col-md-1">
          <p>Thao tác</p>
        </div>
      </div>
      {displayProducts(listProducts)}
      <div className='row'>
        <div className="col-md-1">
          <input type='checkbox' />
        </div>
        <div className="col-md-4">
          <button>Xóa</button>
        </div>
        <div className="col-md-2">
          <p>Tổng thanh toán</p>
        </div>
        <div className="col-md-3">
          120000
        </div>
        <div className="col-md-2">
          <button>Mua hàng</button>
        </div>
      </div>
    </div>
  )
}

export default CartComponent