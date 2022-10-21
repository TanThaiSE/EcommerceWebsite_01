import React,{useEffect,useState} from 'react'
import 'bootstrap/dist/css/bootstrap.min.css';
import 'bootstrap/dist/js/bootstrap.bundle.min.js';
import './index.css';
import NavBarCommon from '../../components/NavBarComon';
import { Link } from 'react-router-dom';
import { useForm } from 'react-hook-form';
import { yupResolver } from '@hookform/resolvers/yup';
import * as yup from 'yup';
import { apiLogin } from '../../api';
import { getLogin, setLogin } from '../../utils/localStorage';
import { useNavigate } from 'react-router-dom';
const schema = yup.object().shape({
  // userName: yup.string().email("Email không hợp lệ").required("Vui lòng điền vào mục này"),
  userName:yup.number("Số điện thoại không hợp lệ").required("Vui lòng điền vào mục này"),
  password: yup.string().required("Vui lòng điền vào mục này")
});

const Login = () => {
  const navigate = useNavigate();
  const { register, handleSubmit, formState: { errors } } = useForm({
    resolver: yupResolver(schema)
  });
  const fetchLogin = async (data) => {
    apiLogin.fetchLogin(data)
      .then((res) => {
        setLogin.saveLogin(res.data.token,res.data.userName);
        navigate("/");
      })
      .catch((err) => {
        console.log('fetchLogin', err);
      })
  }
  const onSubmitLogin = (data) => {
    fetchLogin(data);
  }

  useEffect(() => {
    if(getLogin.getToken()!==null){
      navigate('/');
    }
  }, []);
  return (
    <>
      <NavBarCommon title={`Đăng nhập`} colors={'black'} />
      <div className="loginBackground">
        <div className="container bg-login" style={{ backgroundImage: "url('https://res.cloudinary.com/dwolphrup/image/upload/v1665237930/cart_4_srrov7.jpg')" }}>
          <div className="colm-form">
            <div className="form-container">
              <p className='title-login'>Đăng nhập</p>
              <form onSubmit={handleSubmit(onSubmitLogin)}>
                <input type="text" placeholder="Email address" {...register("userName")} />
                {errors.userName?.message && <span className='content-error'>{errors.userName?.message}</span>}

                <input type="password" placeholder="Password" {...register("password")} />
                {errors.password?.message && <span className='content-error'>{errors.password?.message}</span>}

                <button className="btn-login" type='submit'>Đăng nhập</button>
              </form>
              <div className='d-flex btn-forget'>
                <Link to={"/forgotpassword"} className='link-forgot'>Quên mật khẩu</Link>
              </div>
              <div className="d-flex btn-new">
                <p>Bạn chưa có tài khoản?</p>
                <Link to={"/register"} className='link-regis'>Đăng ký</Link>
              </div>
            </div>
          </div>
        </div>
      </div>
    </>
  )
}

export default Login;