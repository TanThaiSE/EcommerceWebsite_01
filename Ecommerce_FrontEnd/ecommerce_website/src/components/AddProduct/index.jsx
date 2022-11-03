import React, { useEffect, useState,useCallback } from 'react';
import { useNavigate } from 'react-router-dom';
import { Button, Modal, Form } from 'react-bootstrap';
import { useForm } from 'react-hook-form';
import { yupResolver } from '@hookform/resolvers/yup';
import * as yup from 'yup';
import { apiUploadImage,apiAdmin } from '../../api';
import {useDropzone} from 'react-dropzone';
import './index.css';
import ModalSuccess from '../ModalSuccess';

const schema = yup.object().shape({
    name: yup.string().required("Name is required"),
    image:yup.mixed().required('Image is required')
    .test("File format","Image accept files: *.png, *.jpg, *.jpeg"
    ,(file)=>file&&["image/jpeg", "image/png", "image/jpg"].includes(file[0]?.type))
});

const AddProduct = () => {
    const navigate = useNavigate();
    const [showModal, setShowModal] = useState(false);
    const { register, handleSubmit, formState: { errors } } = useForm({
        resolver: yupResolver(schema)
    });

    const onDrop=useCallback((acceptedFiles,rejectFiles)=>{
        console.log('acceptedFiles',acceptedFiles);
        console.log('rejectFiles',rejectFiles);
    },[]);

    const {getRootProps,getInputProps,isDragActive}=useDropzone({
        onDrop,
        accept:{"image/jpeg": [], "image/png": [], "image/jpg": []}
    });

    const handleShowModal = () => { setShowModal(true); }
    const handleCloseModal = () => { setShowModal(false); }

    const handleComeToManageCategory=()=>{
        // handleCloseModal();
        // navigate('/manage-category');
    }
    const handleCreateCategory=async(data)=>{
        // await apiAdmin.fetchCreateCategory(data).then((res)=>{
        //     handleShowModal();
        //     setTimeout(() => {handleComeToManageCategory();}, 1000);

        // }).catch((err)=>console.log(err))

    }
    const onSubmitCreate = async (data) => {
        // await apiUploadImage.uploadImage(data.image[0]).then((res)=>{
        //     handleCreateCategory({name:data.name,image:res.data.url})
        // }).catch((err) => console.log(err));
    }


    return (
        <div>
            <h1>Create Product</h1>
            <form onSubmit={handleSubmit(onSubmitCreate)}>
                <Form.Group className="mb-3">
                    <Form.Label>Name</Form.Label>
                    <Form.Control type="text" {...register("name")} />
                    {errors.name?.message && <span className='content-error'>{errors.name?.message}</span>}
                </Form.Group>

                {/* <Form.Group controlId="formFile" className="mb-3">
                    <Form.Label>Image</Form.Label>
                    <Form.Control type="file" {...register("image")}/>
                    {errors.image?.message && <span className='content-error'>{errors.image?.message}</span>}
                </Form.Group> */}
                <div className='dropzone' {...getRootProps()}>
                    <input {...getInputProps()} />
                     {isDragActive?"Drag Active":"You can drop your files here"}
                </div>
                <div className='d-flex containBtnCategory'>
                    <button className='btn btn-success createCategory' type='submit'>
                        Create
                    </button>
                    <button className="btn btn-secondary backCreateCategory" onClick={() => navigate(-1)}>
                        Back
                    </button>
                </div>
            </form>
            <ModalSuccess title={'New product is created'} handleCloseModal={handleCloseModal} showModal={showModal}/>
        </div>
    )
}

export default AddProduct;

