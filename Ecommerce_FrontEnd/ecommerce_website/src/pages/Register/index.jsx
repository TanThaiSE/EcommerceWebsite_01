import React, { useEffect, useState } from 'react'
import 'bootstrap/dist/css/bootstrap.min.css';
import 'bootstrap/dist/js/bootstrap.bundle.min.js';
import './index.css';
import NavBarCommon from '../../components/NavBarComon';
import { Link } from 'react-router-dom';
import { useForm } from 'react-hook-form';
import { yupResolver } from '@hookform/resolvers/yup';
import * as yup from 'yup';
import { apiProfile, apiRegister } from '../../api';
import { useNavigate } from 'react-router-dom';
import ModalFailed from '../../components/ModalFailed';
import ModalSuccess from '../../components/ModalSuccess';

const schema = yup.object().shape({
  
  phone: yup.string().required("Vui lòng điền vào mục này").matches(/^[0-9]+$/, "Số điện thoại không hợp lệ"),
  email: yup.string().email("Email không hợp lệ").required("Vui lòng điền vào mục này"),
  password: yup.string().required("Vui lòng điền vào mục này")
  .matches(/^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[#$@!%&*?])[A-Za-z\d#$@!%&*?]{8,}$/,"Phải ít nhất 8 ký tự,1 ký tự Hoa,1 ký tự thường,1 số, 1 ký tự đặc biệt"),
  repeatPassword:yup.string().required("Vui lòng điền vào mục này")
  .oneOf([yup.ref('password'), null],"Mật khẩu phải khớp nhau")
  .required("Vui lòng điền vào mục này"),
  
});

const Register = () => {
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

  const fetchRegister = async (data) => {
    await apiRegister.fetchRegister(data)
    .then(async (res)=>{
      if(res.data.code==="201"){
        await apiProfile.fetchAddProfile({accountId:res.data.dataResponse.id}).then((res1)=>{
          handleShowModal();
          setTimeout(() => { handleCloseModal();navigate(`/login`) }, 1000);
        })
        .catch((err1)=>{
          console.log(err1);
        }) 
      }
      else if(res.data.code==="302"){
        handleShowModalFailed();
        setTimeout(() => { handleCloseModalFailed(); }, 3000);
      }
    })
    .catch((err)=>{
      console.log(err);
    })
  }
  const onSubmitLogin = (data) => {
    fetchRegister(data);
  }

  // useEffect(() => {
  //   if (getLogin.getToken() !== null) {
  //     navigate('/');
  //   }
  // }, []);

  return (
    <>
      <NavBarCommon title={`Đăng ký`} colors={'black'} />
      <div className="registerBackground">
        <div className="container bg-register" style={{ backgroundImage: "url('https://res.cloudinary.com/dwolphrup/image/upload/v1665237930/cart_4_srrov7.jpg')" }}>
          <div className="colm-form">
            <div className="form-container">
              <p className='title-login'>Đăng nhập</p>
              <form onSubmit={handleSubmit(onSubmitLogin)}>

                <input type="text" placeholder="Số điện thoại" {...register("phone")} />
                {errors.phone?.message && <span className='content-error'>{errors.phone?.message}</span>}

                <input type="text" placeholder="Địa chỉ email" {...register("email")} />
                {errors.email?.message && <span className='content-error'>{errors.email?.message}</span>}

                <input type="password" placeholder="Mật khẩu" {...register("password")} />
                {errors.password?.message && <span className='content-error'>{errors.password?.message}</span>}

                <input type="password" placeholder="Nhập lại mật khẩu" {...register("repeatPassword")} />
                {errors.repeatPassword?.message && <span className='content-error'>{errors.repeatPassword?.message}</span>}

                <button className="btn-login" type='submit'>Đăng ký</button>
              </form>
              <div className='d-flex btn-forget'>
                <Link to={"/forgotpassword"} className='link-forgot'>Quên mật khẩu</Link>
              </div>
              <div className="d-flex btn-new">
                <p>Bạn đã có tài khoản?</p>
                <Link to={"/login"} className='link-regis'>Đăng nhập</Link>
              </div>
              <ModalSuccess headers={"Thành công"} title={'Đăng ký thành công'} handleCloseModal={handleCloseModal} showModal={showModal} />
              <ModalFailed  headers={"Thất bại"} title={'Số điện thoại hoặc email đã tồn tại'} handleCloseModal={handleCloseModalFailed} showModal={showModalFailed} />
            </div>
          </div>
        </div>
      </div>
    </>
  )
}

export default Register;