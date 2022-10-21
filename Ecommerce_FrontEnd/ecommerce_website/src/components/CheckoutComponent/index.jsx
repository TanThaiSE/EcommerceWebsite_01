import React, { useEffect, useState } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import { apiCart, apiOrderDetail, apiProduct } from '../../api';
import './index.css';
const CheckoutComponent = () => {
  const [listProducts, setListProducts] = useState([]);

  const splitString = (st) => {
    if (st.length !== 0) {
      if (st.length > 72) {
        return st.substring(0, 68) + '...';
      }
      else {
        return st;
      }
    }
  }

  const handleOrder=(listProducts)=>{
    if(listProducts.length>0){
      let tempList=listProducts.map((item)=>{
        return {...item,totalPrice:Number(item.price) * Number(item.quantity)}
      });
      let dataSend={
        orderDetails:tempList,
        address:"123456779",
        paymentId:"d67a99fa-078c-4b32-90eb-b86d1c5f3c9a"
      }
      apiOrderDetail.fetchAddToOrderDetail(dataSend)
      .then(async (res)=>{
        if(res.data.code==="201"){
          await apiCart.fetchDeleteMultipleProduct({prepareToDelete:tempList})
          .then(async(res)=>{
            if(res.data.code==="200")
              {
              await apiProduct.fetchUpdateQuantityProduct({prepareToUpdate:listProducts})
              .then((res)=>console.log('fetchUpdateQuantityProduct',res.data))
              .catch((err)=>console.log(err))
            }
          })
          .then((res)=>console.log('handleOrder finish'))
          .catch((err)=>{console.log(err);});
        }
      })
      .catch((err)=>console.log(err));
    }
  }
  const displayProducts = (listProducts) => {
    if (listProducts.length > 0) {
      return listProducts.map((item, index) => {
        return (
          <div className="row mt-2 mb-5" key={`listProducts-${index}`}>
            <div className="col-md-6">
              <div className="content d-flex" >
                <div className="content-img">
                    <img src={item.imgName} alt={item.productName} />
                </div>
                <div className="content-product">
                  <Link to={`/productdetail/${item.productId}`} className='content-product-link'>
                    <p>{splitString(item.productName)}</p>
                  </Link>
                </div>
                <div className="content-attribute">
                  <p>{item.sizeName},{item.colorName} </p>
                </div>
              </div>
            </div>
            <div className="col-md-2">
              <p>{item.price}</p>
            </div>
            <div className="col-md-2">
              <div className='d-flex'>
                <p className='amountProduct'>{item.quantity}</p>
              </div>
            </div>
            <div className="col-md-2">
              <p>{Number(item.price) * Number(item.quantity)}</p>
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
    if(localStorage.getItem("CHECKOUT")!==null){
      setListProducts(JSON.parse(localStorage.getItem("CHECKOUT")));
    }
  }, []);

  return (
    <div className='container'>
      <div>
        <p>Địa chỉ nhận hàng</p>
        <div>
          <p>0356666666</p>
          <p>123abc</p>
          <button>Thay đổi</button>
        </div>
      </div>

      <div className="row">
        <div className="col-md-6">
          <p>Sản phẩm</p>
        </div>
        <div className="col-md-2">
          <p>Đơn giá</p>
        </div>
        <div className="col-md-2">
          <p>Số lượng</p>
        </div>
        <div className="col-md-2">
          <p>Thành tiền</p>
        </div>
      </div>
      {displayProducts(listProducts)}
      <div className='row'>
        <div className="col-md-7">
            <p>Phương thức thanh toán</p>
        </div>
        <div className="col-md-5">
          <p>Thanh toán khi nhận hàng</p>
        </div>
        <button onClick={()=>{handleOrder(listProducts)}}>Đặt hàng</button>
      </div>
    </div>
  )
}

export default CheckoutComponent;