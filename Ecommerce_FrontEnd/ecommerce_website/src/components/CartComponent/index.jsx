import React from 'react';
import './index.css';
const CartComponent = () => {
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