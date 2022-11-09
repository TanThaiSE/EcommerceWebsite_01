import React, { useState, useEffect } from 'react';
import { Form, Button } from 'react-bootstrap';
import { useForm } from 'react-hook-form';
import { yupResolver } from '@hookform/resolvers/yup';
import * as yup from 'yup';
import './index.css';
import { apiProfile } from '../../../api';
import { getLogin } from '../../../utils/cookieStorage';
import moment from 'moment';
import ModalSuccess from '../../Modals/ModalSuccess';
const schema = yup.object().shape({
  name: yup.string().required("Vui lòng nhập tên"),
  sex: yup.number().typeError('Vui lòng chọn giới tính').required("Vui lòng chọn giới tính").min(0, "Không là số âm"),
  address: yup.string().required("Vui lòng nhập địa chỉ"),
});

const ProfileComponent = () => {
  const [detailUser, setDetailUser] = useState(null);
  const [showModal, setShowModal] = useState(false);
  const { register, handleSubmit, formState: { errors } } = useForm({
    resolver: yupResolver(schema)
  });

  const handleShowModal = () => { setShowModal(true); }
  const handleCloseModal = () => { setShowModal(false); }

  const fetchGetProfile = () => {
    apiProfile.fetchGetDetailProfile(getLogin.getId()).then((res) => {
      setDetailUser(res.data.dataResponse);
    }).catch((err) => console.log(err));
  }

  const onSubmitEditProfile = async (data) => {
    let dataSend={
      id:detailUser?.id,
      accountId:getLogin.getId(),
      name:data.name,
      sex:data.sex,
      birth:new Date(data.birth),
      address:data.address
    }
    
    apiProfile.fetchEditInfoProfile(dataSend).then((res)=>{
      handleShowModal();
      setTimeout(() => {handleCloseModal();}, 1000);
    }).catch((err)=>console.log(err))

  }

  useEffect(() => {
    fetchGetProfile();
  }, []);
  return (
    <div className='profileGeneral'>
      <h1>Hồ sơ của tôi</h1>


      {detailUser !== null ? (
        <>
          <form onSubmit={handleSubmit(onSubmitEditProfile)} >
            <Form.Group className="mb-3">
              <Form.Label>Tên</Form.Label>
              <Form.Control type="text" {...register("name")} defaultValue={detailUser?.name} />
              {errors.name?.message && <span className='content-error'>{errors.name?.message}</span>}
            </Form.Group>

            <Form.Group className="mb-3">
              <Form.Label>Email</Form.Label>
              <Form.Control type="email" disabled defaultValue={detailUser?.accountsProfiles?.email} />
            </Form.Group>

            <Form.Group className="mb-3">
              <Form.Label>Số điện thoại</Form.Label>
              <Form.Control type="text" disabled defaultValue={detailUser?.accountsProfiles?.phone} />
            </Form.Group>

            <Form.Group className="mb-3">
              <Form.Label>Giới tính</Form.Label>
              <div className='d-flex'>
                <Form.Check type="radio" value={0} {...register("sex")} label={'Nam'} style={{ marginRight: '20px' }} defaultChecked={detailUser?.sex === 0 ? true : false} />
                <Form.Check type="radio" value={1} {...register("sex")} label={'Nữ'} defaultChecked={detailUser?.sex === 1 ? true : false} />
              </div>
              {errors.sex?.message && <span className='content-error'>{errors.sex?.message}</span>}
            </Form.Group>

            <Form.Group className="mb-3">
              <Form.Label>Địa chỉ</Form.Label>
              <div className='d-flex'>
                <Form.Control type="text" defaultValue={detailUser?.address} {...register("address")}/>
              </div>
              {errors.address?.message && <span className='content-error'>{errors.address?.message}</span>}
            </Form.Group>

            <Form.Group className="mb-3">
              <Form.Label>Ngày sinh</Form.Label>
              <Form.Control type="date" defaultValue={moment(detailUser?.birth).format("YYYY-MM-DD")} {...register("birth")}/>
            </Form.Group>
            <Button variant="primary"  type='submit' style={{ paddingLeft: '40px', paddingRight: '40px' }} >Lưu</Button>
          </form>
          <ModalSuccess  headers={"Thành công"} title={'Cập nhập thành công'} handleCloseModal={handleCloseModal} showModal={showModal}/>
        </>
      ) : (<></>)}


    </div >

  )
}

export default ProfileComponent;