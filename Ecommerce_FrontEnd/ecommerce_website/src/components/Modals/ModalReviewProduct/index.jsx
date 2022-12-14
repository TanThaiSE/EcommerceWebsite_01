import React, { useEffect, useState } from 'react'
import StarRatings from 'react-star-ratings';
import { Button, Modal } from 'react-bootstrap';
import './index.css';
import { apiRating } from '../../../api';
const ModalReviewProduct = ({ item, handleCloseModal, showModal,handleCommented }) => {
    const [rating, setRating] = useState(5);
    const [comment, setComment] = useState("");
    const handleChangeRating = (rating) => {
        setRating(rating);
    }
    const handleWriteComment = (e) => {
        let { value } = e.target;
        setComment(value);

    }
    const handleReview = async() => {
        let data={
            orderDetailId:item.id,
            pointRate:Number(rating),
            comment:comment,
            productId:item.productId
        };
        await apiRating.fetchAddRating(data)
        .then((res)=>{
            setRating(5);
            setComment("");
            handleCommented();
            handleCloseModal();
        })
        .catch((err)=>console.log(err))
    }

    return (
        <Modal show={showModal} onHide={handleCloseModal}>
            <Modal.Header closeButton>
                <Modal.Title>Đánh giá sản phẩm</Modal.Title>
            </Modal.Header>
            <Modal.Body>
                <div>
                    <span className='nameQuality'>Chất lượng sản phẩm</span>
                    <StarRatings
                        rating={rating}
                        starRatedColor='#ffce3d'
                        numberOfStars={5}
                        starDimension="16px"
                        starSpacing="2px"
                        changeRating={handleChangeRating}
                    />
                </div>
                <div>
                    <p className='mt-2 mb-2'>Bình luận</p>
                    <textarea cols="30" rows="10" onChange={handleWriteComment} placeholder='Hãy chia sẽ những góp ý của bạn về sản phẩm này'></textarea>
                </div>
            </Modal.Body>
            <Modal.Footer>
                <Button variant="secondary" onClick={handleCloseModal}>
                    Trở lại
                </Button>
                <Button variant="primary" onClick={handleReview} className='completeReview'>
                    Hoàn thành
                </Button>
            </Modal.Footer>
        </Modal>

    );
}

export default ModalReviewProduct;