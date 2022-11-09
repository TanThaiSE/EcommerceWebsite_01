import React, { useEffect, useState } from 'react'
import 'bootstrap/dist/css/bootstrap.min.css';
import 'bootstrap/dist/js/bootstrap.bundle.min.js';
import './index.css';
import NavBarCommon from '../../components/Navbars/NavBarComon';
import { Link } from 'react-router-dom';
import { useForm } from 'react-hook-form';
import { yupResolver } from '@hookform/resolvers/yup';
import * as yup from 'yup';
import { apiEmail } from '../../api';
import { useNavigate } from 'react-router-dom';
import ModalFailed from '../../components/Modals/ModalFailed';
import ModalSuccess from '../../components/Modals/ModalSuccess';

const schema = yup.object().shape({
  email: yup.string().email("Email không hợp lệ").required("Vui lòng điền vào mục này"),
});

const ForgotPassword = () => {
  const navigate = useNavigate();
  const { register, handleSubmit, formState: { errors } } = useForm({
    resolver: yupResolver(schema)
  });
  const [showModal, setShowModal] = useState(false);
  const [showModalFailed, setShowModalFailed] = useState(false);
  const handleShowModal = () => { setShowModal(true); }
  const handleCloseModal = () => { setShowModal(false); }

  const handleShowModalFailed = () => { setShowModalFailed(true); }
  const handleCloseModalFailed = () => { setShowModalFailed(false); }

  const fetchGetPassword = async (data) => {
    await apiEmail.fetchSendEmail(data)
      .then(async (res) => {
        handleShowModal();
        setTimeout(() => { handleCloseModal();navigate(`/login`) }, 1000);
      })
      .catch((err) => {
        handleShowModalFailed();
        setTimeout(() => { handleCloseModalFailed(); }, 3000);
      })

  }
  const onSubmitEmail = (data) => {
    fetchGetPassword(data);
  }


  return (
    <>
      <NavBarCommon title={`Đặt lại mật khẩu`} colors={'black'} />
      <div className="registerBackground">
        <div className="container bg-register" style={{ backgroundImage: "url('https://res.cloudinary.com/dwolphrup/image/upload/v1665237930/cart_4_srrov7.jpg')" }}>
          <div className="colm-form">
            <div className="form-container">
              <p className='title-login'>Email</p>
              <form onSubmit={handleSubmit(onSubmitEmail)}>

                <input type="text" placeholder="Địa chỉ email" {...register("email")} />
                {errors.email?.message && <span className='content-error'>{errors.email?.message}</span>}

                <button className="btn-login" type='submit'>Gửi email</button>
              </form>

              <ModalSuccess headers={"Thành công"} title={'Đã gửi mật khẩu vào email'} handleCloseModal={handleCloseModal} showModal={showModal} />
              <ModalFailed headers={"Thất bại"} title={'Email không tồn tại'} handleCloseModal={handleCloseModalFailed} showModal={showModalFailed} />
            </div>
          </div>
        </div>
      </div>
    </>
  )
}

export default ForgotPassword;