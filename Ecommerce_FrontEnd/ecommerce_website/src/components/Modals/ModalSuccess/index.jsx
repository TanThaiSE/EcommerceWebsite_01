import { Modal } from 'react-bootstrap';
import React from 'react';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faCheckCircle } from '@fortawesome/free-solid-svg-icons';
import './index.css';
const ModalSuccess = ({ headers,title, handleCloseModal, showModal }) => {
    return (
        <Modal show={showModal} onHide={handleCloseModal} backdrop="static" keyboard={false}>
            <Modal.Header className='justify-content-center'>
                <Modal.Title id="titleModalSuccess"> {headers}</Modal.Title>
            </Modal.Header>
            <Modal.Body className='text-center'>
                <FontAwesomeIcon icon={faCheckCircle} className='justify-content-center' id="iconSuccess" />
                <Modal.Title id="contentModalSuccess">{title}</Modal.Title>
            </Modal.Body>
        </Modal>
    )
}

export default ModalSuccess;