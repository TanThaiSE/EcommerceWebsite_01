import { Modal } from 'react-bootstrap';
import React from 'react';
import './index.css';
import MoonLoader from "react-spinners/MoonLoader";
const ModalLoadding = ({ title, handleCloseModal, showModal }) => {
    return (
        <Modal show={showModal} onHide={handleCloseModal} backdrop="static" keyboard={false}>
            <Modal.Header className='justify-content-center'>
                <MoonLoader loading={showModal} size={50} color={'rgb(18, 198, 204)'}/>
            </Modal.Header>
            <Modal.Body className='text-center'>
                <Modal.Title id="contentModalSuccess">{title}</Modal.Title>
            </Modal.Body>
        </Modal>
    )
}

export default ModalLoadding;