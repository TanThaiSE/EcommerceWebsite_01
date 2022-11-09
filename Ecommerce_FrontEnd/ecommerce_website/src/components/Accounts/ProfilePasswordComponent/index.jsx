import React, { useState, useEffect } from 'react';
import { Form, Button } from 'react-bootstrap';
import { useForm } from 'react-hook-form';
import { yupResolver } from '@hookform/resolvers/yup';
import * as yup from 'yup';
import './index.css';
import { apiAccount, apiProfile } from '../../../api';
import { getLogin } from '../../../utils/cookieStorage';
import ModalSuccess from '../../Modals/ModalSuccess';
import ModalFailed from '../../Modals/ModalFailed';
const schema = yup.object().shape({
  oldPassword: yup.string().required("Vui lòng điền vào mục này"),
  newPassword: yup.string().required("Vui lòng điền vào mục này")
    .matches(/^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[#$@!%&*?])[A-Za-z\d#$@!%&*?]{8,}$/, "Phải ít nhất 8 ký tự,1 ký tự Hoa,1 ký tự thường,1 số, 1 ký tự đặc biệt"),
  repeatPassword: yup.string().required("Vui lòng điền vào mục này")
    .oneOf([yup.ref('newPassword'), null], "Mật khẩu phải khớp nhau")
    .required("Vui lòng điền vào mục này"),
});

const ProfilePasswordComponent = () => {
  const [detailUser, setDetailUser] = useState(null);
  const [showModal, setShowModal] = useState(false);
  const [showModalFailed,setShowModalFailed]= useState(false);
  const { register, handleSubmit, formState: { errors } } = useForm({
    resolver: yupResolver(schema)
  });

  const handleShowModal = () => { setShowModal(true); }
  const handleCloseModal = () => { setShowModal(false); }
  const handleShowModalFailed = () => { setShowModalFailed(true); }
  const handleCloseModalFailed = () => { setShowModalFailed(false); }
  
  const fetchGetProfile = () => {
    apiProfile.fetchGetDetailProfile(getLogin.getId()).then((res) => {
      setDetailUser(res.data.dataResponse);
    }).catch((err) => console.log(err));
  }

  const onSubmitEditProfile = async (data) => {
    let dataSend = {
      id: getLogin.getId(),
      oldPassword: data.oldPassword,
      newPassword: data.newPassword,
    }

    apiAccount.fetchUpdatePassword(dataSend).then((res) => {
      handleShowModal();
      setTimeout(() => { handleCloseModal(); }, 1000);
    }).catch((err) => {
      console.log(err);
      handleShowModalFailed();
      setTimeout(() => { handleCloseModalFailed(); }, 1000);
    })

  }

  useEffect(() => {
    fetchGetProfile();
  }, []);
  return (
    <div className='profileGeneral'>
      <h1>Đổi mật khẩu</h1>


      {detailUser !== null ? (
        <>
          <form onSubmit={handleSubmit(onSubmitEditProfile)} >
            <Form.Group className="mb-3">
              <Form.Label>Mật khẩu hiện tại</Form.Label>
              <Form.Control type="password" {...register("oldPassword")} />
              {errors.oldPassword?.message && <span className='content-error'>{errors.oldPassword?.message}</span>}
            </Form.Group>

            <Form.Group className="mb-3">
              <Form.Label>Mật khẩu mới</Form.Label>
              <Form.Control type="password"  {...register("newPassword")} />
              {errors.newPassword?.message && <span className='content-error'>{errors.newPassword?.message}</span>}
            </Form.Group>

            <Form.Group className="mb-3">
              <Form.Label>Xác nhận mật khẩu</Form.Label>
              <Form.Control type="password" {...register("repeatPassword")} />
              {errors.repeatPassword?.message && <span className='content-error'>{errors.repeatPassword?.message}</span>}
            </Form.Group>

            <Button variant="primary" type='submit' style={{ paddingLeft: '40px', paddingRight: '40px' }} >Xác nhận</Button>
          </form>
          <ModalSuccess headers={"Thành công"} title={'Cập nhập mật khẩu thành công'} handleCloseModal={handleCloseModal} showModal={showModal} />
          <ModalFailed  headers={"Thất bại"} title={'Mật khẩu không đúng'} handleCloseModal={handleCloseModalFailed} showModal={showModalFailed} />
        </>
      ) : (<></>)}


    </div >

  )
}

export default ProfilePasswordComponent;