import React from 'react';
import { Button, Modal, Form } from 'react-bootstrap';
import moment from 'moment';
import './index.css';

const ModalInfoUser = ({ detailUser, handleCloseModalAccount, showModalAccount }) => {
    return (
        <Modal show={showModalAccount} onHide={handleCloseModalAccount} size="lg">
            <Modal.Header closeButton>
                <Modal.Title>Detail Information</Modal.Title>
            </Modal.Header>
            <Modal.Body>
                <Form.Group className="mb-3">
                    <Form.Label>ID</Form.Label>
                    <Form.Control placeholder={detailUser?.accountsProfiles?.id} disabled />
                </Form.Group>

                <Form.Group className="mb-3">
                    <Form.Label>Full Name</Form.Label>
                    <Form.Control placeholder={detailUser?.name} disabled />
                </Form.Group>

                <Form.Group className="mb-3">
                    <Form.Label>Gender</Form.Label>
                    {detailUser?.sex===0?(<Form.Control placeholder='Male' disabled />)
                    :(<Form.Control placeholder='Female' disabled />)}
                </Form.Group>

                <Form.Group className="mb-3">
                    <Form.Label>Email</Form.Label>
                    <Form.Control placeholder={detailUser?.accountsProfiles?.email} disabled />
                </Form.Group>

                <Form.Group className="mb-3">
                    <Form.Label>Date of birth</Form.Label>
                    <Form.Control placeholder={moment(detailUser?.birth).format("DD/MM/YYYY")} disabled />
                </Form.Group>

                <Form.Group className="mb-3">
                    <Form.Label>Phone number</Form.Label>
                    <Form.Control placeholder={detailUser?.accountsProfiles?.phone} disabled />
                </Form.Group>
                <Form.Group className="mb-3">
                    <Form.Label>Address</Form.Label>
                    <Form.Control as="textarea" rows={3} placeholder={detailUser?.address} disabled />
                </Form.Group>


            </Modal.Body>
            <Modal.Footer>
                <Button variant="secondary" onClick={handleCloseModalAccount}>
                    Back
                </Button>
            </Modal.Footer>
        </Modal>

    );
}

export default ModalInfoUser;