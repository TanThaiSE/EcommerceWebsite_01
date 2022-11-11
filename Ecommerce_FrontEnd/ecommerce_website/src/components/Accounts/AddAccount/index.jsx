import React, { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { Form } from 'react-bootstrap';
import { useForm } from 'react-hook-form';
import { yupResolver } from '@hookform/resolvers/yup';
import * as yup from 'yup';
import { apiAccount } from '../../../api';
import moment from 'moment';
import './index.css';

import ModalSuccess from '../../Modals/ModalSuccess';
import ModalLoadding from '../../Modals/ModalLoadding';
import ModalFailed from '../../Modals/ModalFailed';

const schema = yup.object().shape({
    name: yup.string().required("Name is required"),
    sex: yup.number().typeError('Gender is required').required("Gender is required").min(0, "Minimum is 0"),
    email: yup.string().email("Email not valid").required("Email is required"),
    phone: yup.string().required("Phone is required").matches(/^[0-9]+$/, "Phone not valid"),
    address: yup.string().required("Address is required"),
    birth: yup.date().required('birth is required'),
    newPassword: yup.string().required("New password is required")
        .matches(/^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[#$@!%&*?])[A-Za-z\d#$@!%&*?]{8,}$/, "At least 8 characters,1 Uppercase,1 Lowercase,1 number, 1 special character"),
    repeatPassword: yup.string().required("Repeat password is required")
        .oneOf([yup.ref('newPassword'), null], "Password not match")
        .required("Repeat password is required"),
});

const AddAccount = () => {
    const navigate = useNavigate();
    const [showModal, setShowModal] = useState(false);
    const [loadingCreateProduct, setLoadingCreateProduct] = useState(false);
    const [showModalFailed,setShowModalFailed]= useState(false);
    const { register, handleSubmit, formState: { errors } } = useForm({
        resolver: yupResolver(schema)
    });


    const handleShowModal = () => { setShowModal(true); }
    const handleCloseModal = () => { setShowModal(false); }

    const handleShowLoading = () => { setLoadingCreateProduct(true); }
    const handleCloseLoading = () => { setLoadingCreateProduct(false); }

    const handleShowModalFailed = () => { setShowModalFailed(true); }
    const handleCloseModalFailed = () => { setShowModalFailed(false); }

    const handleComeToManageCustomer = () => {
        handleCloseModal();
        navigate('/manage-account');
    }


    const onSubmitCreateCustomer = async (data) => {
        handleShowLoading();
        let dataSend={
            email:data.email,
            password:data.newPassword,
            phone:data.phone,
            name :data.name,
            sex:data.sex,
            birth:new Date(data.birth),
            address:data.address
        }
        apiAccount.fetchCreateAccount(dataSend).then((res)=>{
            if(res.data.code==='302'){
                handleCloseLoading();
                handleShowModalFailed();
                setTimeout(() => { handleCloseModalFailed(); }, 1000);
            }
            if(res.data.code==='201'){
                handleCloseLoading();
                handleShowModal();
                setTimeout(() => { handleComeToManageCustomer(); }, 1000);
            }

        }).catch((err)=>console.log(err))
    }

    return (
        <div>
            <h1>Create Customer</h1>
            <form onSubmit={handleSubmit(onSubmitCreateCustomer)}>
                <div>
                    <Form.Group className="mb-3">
                        <Form.Label>Full Name</Form.Label>
                        <Form.Control type="text" {...register("name")} />
                        {errors.name?.message && <span className='content-error'>{errors.name?.message}</span>}
                    </Form.Group>
                </div>

                <div className="row mt-3">
                    <div className="col-md-6 mb-3">
                        <Form.Group className="mb-3">
                            <Form.Label>Gender</Form.Label>
                            <div className='d-flex'>
                                <Form.Check type="radio" value={0} {...register("sex")} label={'Male'} style={{ marginRight: '20px' }} />
                                <Form.Check type="radio" value={1} {...register("sex")} label={'Female'} />
                            </div>
                            {errors.sex?.message && <span className='content-error'>{errors.sex?.message}</span>}
                        </Form.Group>
                    </div>

                    <div className='col-md-6 mb-3'>
                        <Form.Group className="mb-3">
                            <Form.Label>Date of birth</Form.Label>
                            <Form.Control type="date"  {...register("birth")} defaultValue={moment(new Date()).format("YYYY-MM-DD")}/>
                        </Form.Group>
                        {errors.birth?.message && <span className='content-error'>{errors.birth?.message}</span>}
                    </div>
                </div>


                <Form.Group className="mb-3">
                    <Form.Label>Email</Form.Label>
                    <Form.Control type="email" {...register("email")} />
                    {errors.email?.message && <span className='content-error'>{errors.email?.message}</span>}
                </Form.Group>

                <Form.Group className="mb-3">
                    <Form.Label>Phone number</Form.Label>
                    <Form.Control type="text"  {...register("phone")}/>
                    {errors.phone?.message && <span className='content-error'>{errors.phone?.message}</span>}
                </Form.Group>

                <Form.Group className="mb-3">
                    <Form.Label>Address</Form.Label>
                    <Form.Control type="text"  {...register("address")} />
                    {errors.address?.message && <span className='content-error'>{errors.address?.message}</span>}
                </Form.Group>

                <Form.Group className="mb-3">
                    <Form.Label>Password</Form.Label>
                    <Form.Control type="password"  {...register("newPassword")} />
                    {errors.newPassword?.message && <span className='content-error'>{errors.newPassword?.message}</span>}
                </Form.Group>

                <Form.Group className="mb-3">
                    <Form.Label>Repeat password</Form.Label>
                    <Form.Control type="password" {...register("repeatPassword")} />
                    {errors.repeatPassword?.message && <span className='content-error'>{errors.repeatPassword?.message}</span>}
                </Form.Group>




                <div className='d-flex containBtnCategory'>
                    <button className="btn btn-secondary backCreateCategory" onClick={() => navigate(-1)}>
                        Back
                    </button>
                    <button className='btn btn-success createCategory' type='submit'>
                        Create
                    </button>
                </div>
            </form>
            <ModalSuccess headers={"Success"} title={'New customer is created'} handleCloseModal={handleCloseModal} showModal={showModal} />
            <ModalLoadding title={'Please wait to create customer'} handleCloseModal={handleCloseLoading} showModal={loadingCreateProduct} />
            <ModalFailed  headers={"Failed"} title={'Email or phone is existed'} handleCloseModal={handleCloseModalFailed} showModal={showModalFailed} />
        </div>
    )
}

export default AddAccount;

