import React, { useEffect, useState } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import { apiCart, apiOrderDetail, apiPayment, apiProduct } from '../../api';
import { getLogin } from '../../utils/cookieStorage';
import Select from 'react-select';
import './index.css';
import { useForm } from 'react-hook-form';
import { yupResolver } from '@hookform/resolvers/yup';
import * as yup from 'yup';
import ModalSuccess from '../ModalSuccess';

const schema = yup.object().shape({
  address: yup.string().required("Vui lòng nhập địa chỉ")
});

const CheckoutComponent = () => {
  const [listProducts, setListProducts] = useState([]);
  const [selectedOptionPayment, setSelectedOptionPayment] = useState([]);
  const [payment, setPayment] = useState([]);
  const [showModal, setShowModal] = useState(false);
  const { register, handleSubmit, formState: { errors }, reset } = useForm({
    resolver: yupResolver(schema)
  });
  const navigate = useNavigate();
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
  const handleShowModal = () => { setShowModal(true); }
  const handleCloseModal = () => { setShowModal(false); }
  // const handleOrder = (listProducts) => {
    // if (listProducts.length > 0) {
    //   let tempList = listProducts.map((item) => {
    //     return { ...item, totalPrice: Number(item.price) * Number(item.quantity) }
    //   });
    //   let dataSend = {
    //     orderDetails: tempList,
    //     address: "123456779",
    //     paymentId: selectedOptionPayment?.id
    //   }
    //   apiOrderDetail.fetchAddToOrderDetail(dataSend)
    //     .then(async (res) => {
    //       if (res.data.code === "201") {
    //         await apiCart.fetchDeleteMultipleProduct({ prepareToDelete: tempList })
    //           .then(async (res) => {
    //             if (res.data.code === "200") {
    //               await apiProduct.fetchUpdateQuantityProduct({ prepareToUpdate: listProducts })
    //                 .then((res) => console.log('fetchUpdateQuantityProduct', res.data))
    //                 .catch((err) => console.log(err))
    //             }
    //           })
    //           .then((res) => console.log('handleOrder finish'))
    //           .catch((err) => { console.log(err); });
    //       }
    //     })
    //     .catch((err) => console.log(err));
    // }

  // }
  const handleComeToOrder = () => {
    handleCloseModal();
    navigate('/user/purchase');
  }
  const onSubmitOrderProduct = async (data) => {
  if (listProducts.length > 0) {
    let tempList = listProducts.map((item) => {
      return { ...item, totalPrice: Number(item.price) * Number(item.quantity) }
    });
    let dataSend = {
      orderDetails: tempList,
      address: data.address,
      paymentId: selectedOptionPayment?.id
    }
    apiOrderDetail.fetchAddToOrderDetail(dataSend)
      .then(async (res) => {
        if (res.data.code === "201") {
          await apiCart.fetchDeleteMultipleProduct({ prepareToDelete: tempList })
            .then(async (res) => {
              if (res.data.code === "200") {
                await apiProduct.fetchUpdateQuantityProduct({ prepareToUpdate: listProducts })
                  .then((res) => {
                    handleShowModal();
                    setTimeout(() => { handleComeToOrder(); }, 1000);
                  })
                  .catch((err) => console.log(err))
              }
            })
            .then((res) => console.log('handleOrder finish'))
            .catch((err) => { console.log(err); });
        }
      })
      .catch((err) => console.log(err));
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

  const handleChangePayment = e => setSelectedOptionPayment(e);

  const handleGetPayment = async () => {
    await apiPayment.fetchGetAllPayment()
      .then((res) => {
        let tempPayment = res.data.dataResponse.map((item) => {
          return { ...item, label: item.name, value: item.name }
        })
        setSelectedOptionPayment(tempPayment[1]);
        setPayment(tempPayment)
      })
      .catch((err) => console.log(err));
  }

  useEffect(() => {
    handleGetPayment();
  }, []);

  useEffect(() => {
    if (localStorage.getItem("CHECKOUT") !== null) {
      setListProducts(JSON.parse(localStorage.getItem("CHECKOUT")));
    }
  }, []);

  return (
    <div className='container'>
      <form onSubmit={handleSubmit(onSubmitOrderProduct)}>
      <div>
        <p className='nameAddress'>Địa chỉ nhận hàng</p>
        <div className='d-flex'>
          <p className='fullname'>{getLogin.getUserName()}</p>
          <div style={{ marginLeft: '101px', width: '100%' }}>
            <input type="text" {...register("address")} placeholder="Nhập địa chỉ" className='addressReceive' />
            {errors.address?.message && <p className='content-error'>{errors.address?.message}</p>}
          </div>

        </div>
      </div>

      <div className="row mt-4 mb-4">
        <div className="col-md-6">
          <p className='fw-bold'>Sản phẩm</p>
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
          <p className='paymentMethod'>Phương thức thanh toán</p>
        </div>
        <div className="col-md-5">
          <Select
            closeMenuOnSelect={false}
            placeholder="Chọn phương thức thanh toán"
            value={selectedOptionPayment}
            options={payment}
            onChange={handleChangePayment}
            className="selectPayment"
          />
          <button type='submit' className='btnOrder'>Đặt hàng</button>
          {/* <button onClick={() => { handleOrder(listProducts) }} className='btnOrder'>Đặt hàng</button> */}
        </div>

      </div>
      </form>
      <ModalSuccess headers={"Success"} title={'Product is edited'} handleCloseModal={handleCloseModal} showModal={showModal} />
    </div>
  )
}

export default CheckoutComponent;