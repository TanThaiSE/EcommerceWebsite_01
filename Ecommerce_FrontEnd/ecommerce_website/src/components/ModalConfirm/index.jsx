import React from 'react';
import { Button, Modal } from 'react-bootstrap';
import './index.css';

const ModalConfirm = ({ title,handleCloseModal,showModal,handleWork,item }) => {
    return (
        <Modal show={showModal} onHide={handleCloseModal} size="lg">
            <Modal.Header closeButton>
                <Modal.Title>Confirm</Modal.Title>
            </Modal.Header>
            <Modal.Body>{title}</Modal.Body>
            <Modal.Footer>
                <Button variant="secondary" onClick={handleCloseModal}>
                    Back
                </Button>
                <Button variant="primary" onClick={()=>{handleWork(item.accountsProfiles.id,item.id)}}>
                    Confirm
                </Button>
            </Modal.Footer>
        </Modal>

    );
}

export default ModalConfirm;