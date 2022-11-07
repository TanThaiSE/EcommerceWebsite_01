import { Modal } from 'react-bootstrap';
import React from 'react';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faX } from '@fortawesome/free-solid-svg-icons';
import './index.css';
const ModalFailed = ({ headers,title, handleCloseModal, showModal }) => {
    return (
        <Modal show={showModal} onHide={handleCloseModal} backdrop="static" keyboard={false}>
            <Modal.Header className='justify-content-center'>
                <Modal.Title id="titleModalFailed"> {headers}</Modal.Title>
            </Modal.Header>
            <Modal.Body className='text-center'>
                <FontAwesomeIcon icon={faX} className='justify-content-center' id="iconFailed" />
                <Modal.Title id="contentModalFailed">{title}</Modal.Title>
            </Modal.Body>
        </Modal>
    )
}

export default ModalFailed;