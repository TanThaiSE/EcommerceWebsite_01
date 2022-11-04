import React,{useState} from 'react';
import { Button, Modal, Form } from 'react-bootstrap';
import { useForm } from 'react-hook-form';
import { yupResolver } from '@hookform/resolvers/yup';
import * as yup from 'yup';
import './index.css';
import { apiUploadImage } from '../../api';

const schema = yup.object().shape({
    name: yup.string().required("Name is required")
});

const ModalInfoCategory = ({ detailCategory, handleCloseModal, showModal,handleWork }) => {
    const [imageUpload,setImageUpload]=useState("");
    const [errorImg,setErrorImg]=useState("");
    const { register, handleSubmit, formState: { errors } } = useForm({
        resolver: yupResolver(schema)
    });

    const onSubmitEdit =async (data) => {
        if(errorImg){
            return;
        }
        if(imageUpload!==""){
            await apiUploadImage.uploadImage(imageUpload).then((res)=>{
                handleWork({id:detailCategory?.id,name:data.name,image:res.data.url})
            }).catch((err)=>console.log(err));
        }
        else{
            handleWork({id:detailCategory?.id,name:data.name,image:detailCategory?.image});
        }
    }

    const handleChangeImage=(event)=>{
        console.log(event.target.files);
        let image=event.target.files[0];
        if (image.size === 0 || image.type.split("/")[0] !== "image" || image.size > 1048576){
            setErrorImg("Image accept files: *.png, *.jpg, *.jpeg");
            return;
        }
        setImageUpload(image);
    }
    return (
        <Modal show={showModal} onHide={handleCloseModal} size="lg">
            <Modal.Header closeButton>
                <Modal.Title>Detail Information</Modal.Title>
            </Modal.Header>
            <form onSubmit={handleSubmit(onSubmitEdit)}>
                <Modal.Body>
                    <Form.Group className="mb-3">
                        <Form.Label>ID</Form.Label>
                        <Form.Control placeholder={detailCategory?.id} disabled />
                    </Form.Group>

                
                    <Form.Group controlId="formFile" className="mb-3">
                        <Form.Label>Image</Form.Label>
                        <Form.Control type="file" name="image" onChange={handleChangeImage}/>
                        {errorImg && <span className='content-error'>{errorImg}</span>}
                    </Form.Group>

                    <Form.Group className="mb-3">
                        <Form.Label>Name</Form.Label>
                        <Form.Control type="text" defaultValue={detailCategory?.name} {...register("name")} />
                        {errors.name?.message && <span className='content-error'>{errors.name?.message}</span>}
                    </Form.Group>

                </Modal.Body>
                <Modal.Footer>
                    <Button variant="secondary" onClick={handleCloseModal}>
                        Cancel
                    </Button>
                    <Button variant="primary" type='submit'>
                        OK
                    </Button>
                </Modal.Footer>
            </form>
        </Modal>

    );
}

export default ModalInfoCategory;