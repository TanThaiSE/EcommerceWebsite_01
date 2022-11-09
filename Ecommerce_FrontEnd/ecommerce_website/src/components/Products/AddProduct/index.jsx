import React, { useEffect, useState, useCallback, useRef } from 'react';
import { useNavigate } from 'react-router-dom';
import { Form } from 'react-bootstrap';
import { useForm } from 'react-hook-form';
import { yupResolver } from '@hookform/resolvers/yup';
import * as yup from 'yup';
import { apiUploadImage, apiCategory, apiProduct, apiColor, apiSize, apiImage } from '../../../api';
import { useDropzone } from 'react-dropzone';
import './index.css';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faX } from '@fortawesome/free-solid-svg-icons';
import ModalSuccess from '../../Modals/ModalSuccess';
import Select from 'react-select';
import { Editor } from '@tinymce/tinymce-react';

import ModalLoadding from '../../Modals/ModalLoadding';


const schema = yup.object().shape({
    name: yup.string().required("Name is required"),
    price: yup.number().typeError('Price is required').required("Price is required").min(0, "Not negative number")
});

const AddProduct = () => {
    const navigate = useNavigate();
    const [showModal, setShowModal] = useState(false);
    const [size, setSize] = useState([]);
    const [color, setColor] = useState([]);
    const [category, setCategory] = useState([]);
    const [images, setImages] = useState([]);
    const [errorImg, setErrorImg] = useState(false);
    const [errorSize, setErrorSize] = useState(false);
    const [errorColor, setErrorColor] = useState(false);
    const [errorCategory, setErrorCategory] = useState(false);
    const [loadingCreateProduct, setLoadingCreateProduct] = useState(false);
    const { register, handleSubmit, formState: { errors } } = useForm({
        resolver: yupResolver(schema)
    });
    const [selectedOptionSize, setSelectedOptionSize] = useState([]);
    const [selectedOptionColor, setSelectedOptionColor] = useState([]);
    const [selectedOptionCategory, setSelectedOptionCategory] = useState([]);
    const editorRefDetail = useRef();
    const editorRefDescription = useRef();

    const onDrop = useCallback((acceptedFiles, rejectFiles) => {
        setImages(
            acceptedFiles.map(file => Object.assign(file, {
                preview: URL.createObjectURL(file)
            })));
    }, []);

    const { getRootProps, getInputProps, isDragReject } = useDropzone({
        onDrop, accept: { "image/jpeg": [], "image/png": [], "image/jpg": [] }
    });

    const handleShowModal = () => { setShowModal(true); }
    const handleCloseModal = () => { setShowModal(false); }

    const handleShowLoading = () => { setLoadingCreateProduct(true); }
    const handleCloseLoading = () => { setLoadingCreateProduct(false); }

    const handleComeToManageProduct = () => {
        handleCloseModal();
        navigate('/manage-product');
    }

    const handleDeleteImg = (img) => {
        let indexImg = images.findIndex((item) => item.name === img.name);
        if (indexImg === -1) {
            return;
        }
        let lstImageTemp = [...images];
        lstImageTemp.splice(indexImg, 1);
        setImages(lstImageTemp);
    }
    const onSubmitCreateProduct = async (data) => {
        let detail = '';
        let description = '';
        if (selectedOptionSize.length <= 0) {
            setErrorSize(true);
        }
        else {
            setErrorSize(false);
        }
        if (selectedOptionColor.length <= 0) {
            setErrorColor(true);
        }
        else {
            setErrorColor(false);
        }
        if (images.length <= 0) {
            setErrorImg(true);
        }
        else {
            setErrorImg(false);
        }
        if (selectedOptionCategory.length <= 0) {
            setErrorCategory(true);
        }
        else {
            setErrorCategory(false);
        }
        if (editorRefDetail.current) {
            detail = editorRefDetail.current.getContent();
        }
        if (editorRefDescription.current) {
            description = editorRefDescription.current.getContent();
        }

        if (errorImg && errorSize && errorImg && errorCategory) {
            return;
        }
        handleShowLoading();
        let productCreate = {
            name: data.name,
            detail: detail,
            description: description,
            price: Number(data.price),
            categoryId: selectedOptionCategory.id
        };



        await apiProduct.fetchCreateProduct(productCreate).then(async (res) => {
            let idProduct = res.data.dataResponse.id;
            let listImageInCloud = [];
            await apiColor.fetchCreateColorProduct({ listAttributes: selectedOptionColor, productId: idProduct }).then((res) => {
            }).catch((err) => console.log(err));
            await apiSize.fetchCreateSizeProduct({ listAttributes: selectedOptionSize, productId: idProduct }).then((res) => {
            }).catch((err) => console.log(err));

            for (let i = 0; i < images.length; i++) {
                await apiUploadImage.uploadImage(images[i]).then((res) => {
                    listImageInCloud.push({ nameImage: res.data.url, indexImage: Number(i), productId: idProduct });
                }).catch((err) => console.log(err));
            }
            await apiImage.fetchCreateImageProduct({ listImages: listImageInCloud }).then((res) => {
                handleCloseLoading();
                handleShowModal();
                setTimeout(() => { handleComeToManageProduct(); }, 1000);
            }).catch((err) => console.log(err));

        }).catch((err) => console.log(err));




    }
    const handleChangeSize = e => setSelectedOptionSize(e);
    const handleChangeColor = e => setSelectedOptionColor(e);
    const handleChangeCategory = e => setSelectedOptionCategory(e);

    const fetchSize = async () => {
        await apiSize.fetchGetSize().then((res) => {
            let tempSize = res.data.dataResponse.map((item) => {
                return { ...item, label: item.name, value: item.name }
            });
            setSize(tempSize);
        }).catch((err) => console.log(err))
    }

    const fetchColor = async () => {
        await apiColor.fetchGetColor().then((res) => {
            let tempSize = res.data.dataResponse.map((item) => {
                return { ...item, label: item.name, value: item.name }
            });
            setColor(tempSize);
        }).catch((err) => console.log(err))
    }

    const fetchCategory = async () => {
        await apiCategory.fetchCategory().then((res) => {
            let tempCategory = res.data.map((item) => {
                return { ...item, label: item.name, value: item.name }
            });
            setCategory(tempCategory);
        }).catch((err) => console.log(err))
    }
    useEffect(() => {
        fetchSize();
    }, []);
    useEffect(() => {
        fetchColor();
    }, []);
    useEffect(() => {
        fetchCategory();
    }, []);
    return (
        <div>
            <h1>Create Product</h1>
            <form onSubmit={handleSubmit(onSubmitCreateProduct)}>
                <div>
                    <Form.Group className="mb-3">
                        <Form.Label>Name</Form.Label>
                        <Form.Control type="text" {...register("name")} />
                        {errors.name?.message && <span className='content-error'>{errors.name?.message}</span>}
                    </Form.Group>
                </div>
                <div>
                    <Form.Group className="mt-3 mb-3">
                        <Form.Label>Detail</Form.Label>
                    </Form.Group>
                    <Editor
                        onInit={(evt, editor) => editorRefDetail.current = editor}
                        init={{
                            height: 300,
                            width: '100%',
                            menubar: true,
                            plugins: [
                                'a11ychecker', 'advlist', 'advcode', 'advtable', 'autolink', 'checklist', 'export',
                                'lists', 'link', 'image', 'charmap', 'preview', 'anchor', 'searchreplace', 'visualblocks',
                                'powerpaste', 'fullscreen', 'formatpainter', 'insertdatetime', 'media', 'table', 'help', 'wordcount'
                            ],
                            toolbar: 'undo redo | casechange blocks | bold italic backcolor | ' +
                                'alignleft aligncenter alignright alignjustify | ' +
                                'bullist numlist checklist outdent indent | removeformat | a11ycheck code table help',
                            content_style: 'body { font-family:Helvetica,Arial,sans-serif; font-size:14px }'
                        }}
                    />
                </div>

                <div>
                    <Form.Group className="mt-3 mb-3">
                        <Form.Label>Description</Form.Label>
                    </Form.Group>
                    <Editor
                        onInit={(evt, editor) => editorRefDescription.current = editor}
                        init={{
                            height: 300,
                            width: '100%',
                            menubar: true,
                            plugins: [
                                'a11ychecker', 'advlist', 'advcode', 'advtable', 'autolink', 'checklist', 'export',
                                'lists', 'link', 'image', 'charmap', 'preview', 'anchor', 'searchreplace', 'visualblocks',
                                'powerpaste', 'fullscreen', 'formatpainter', 'insertdatetime', 'media', 'table', 'help', 'wordcount'
                            ],
                            toolbar: 'undo redo | casechange blocks | bold italic backcolor | ' +
                                'alignleft aligncenter alignright alignjustify | ' +
                                'bullist numlist checklist outdent indent | removeformat | a11ycheck code table help',
                            content_style: 'body { font-family:Helvetica,Arial,sans-serif; font-size:14px }'
                        }}
                    />
                </div>

                <div className="row mt-3">
                    <div className="col-md-6 mb-3">
                        <Form.Group controlId="formFile" className="mb-3">
                            <Form.Label>Size</Form.Label>
                        </Form.Group>
                        <Select
                            isMulti
                            closeMenuOnSelect={false}
                            placeholder="Select Size"
                            value={selectedOptionSize}
                            options={size}
                            onChange={handleChangeSize}
                        />
                        {errorSize && <span className='content-error'>Size is required</span>}
                    </div>
                    <div className="col-md-6 mb-3">
                        <Form.Group controlId="formFile" className="mb-3">
                            <Form.Label>Color</Form.Label>
                        </Form.Group>
                        <Select
                            isMulti
                            closeMenuOnSelect={false}
                            placeholder="Select Color"
                            value={selectedOptionColor}
                            options={color}
                            onChange={handleChangeColor}
                        />
                        {errorColor && <span className='content-error'>Color is required</span>}
                    </div>
                    <div className="col-md-6 mb-3">
                        <Form.Group controlId="formFile" className="mb-3">
                            <Form.Label>Category</Form.Label>
                        </Form.Group>
                        <Select
                            placeholder="Select Category"
                            value={selectedOptionCategory}
                            options={category}
                            onChange={handleChangeCategory}
                        />
                        {errorCategory && <span className='content-error'>Category is required</span>}
                    </div>
                    <div className='col-md-6 mb-3'>
                        <Form.Group className="mb-3">
                            <Form.Label>Price</Form.Label>
                        </Form.Group>
                        <Form.Control type="number" {...register("price")} className='priceProduct' />
                        {errors.price?.message && <span className='content-error'>{errors.price?.message}</span>}
                    </div>
                </div>

                <div>
                    <Form.Group controlId="formFile" className="mb-3">
                        <Form.Label>Image</Form.Label>
                    </Form.Group>
                    <div className='dropzone' {...getRootProps()}>
                        <input {...getInputProps()} />
                        {isDragReject ? (
                            <>
                                <p className='text-center'>Type file is not supported</p>
                            </>
                        ) : (
                            <div className=''>
                                <p className='text-center'>Drag and Drop the file here</p>
                                <p className='text-center'>Only *.png, *.jpg, *.jpeg files are supported</p>
                            </div>
                        )}
                    </div>
                    {errorImg === true && <span className='content-error'>Image is required</span>}
                </div>


                {images.length > 0 &&
                    <div className='row mt-5'>
                        {images.map((item, index) => {
                            return (
                                <div className='col-md-2 mt-3' key={`img-upload-${index}`}>
                                    <div className="card" style={{ width: '8rem' }}>
                                        <div className="card-title text-end">
                                            <FontAwesomeIcon icon={faX} onClick={() => { handleDeleteImg(item) }} />
                                        </div>
                                        <img className="card-img-top" src={item.preview} alt={item.name} />
                                    </div>
                                </div>);
                        })}
                    </div>}


                <div className='d-flex containBtnCategory'>
                    <button className="btn btn-secondary backCreateCategory" onClick={() => navigate(-1)}>
                        Back
                    </button>
                    <button className='btn btn-success createCategory' type='submit'>
                        Create
                    </button>
                </div>
            </form>
            <ModalSuccess headers={"headers"} title={'New product is created'} handleCloseModal={handleCloseModal} showModal={showModal} />
            <ModalLoadding title={'Please wait to create product'} handleCloseModal={handleCloseLoading} showModal={loadingCreateProduct}/>
        </div>
    )
}

export default AddProduct;

