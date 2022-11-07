import React, { useEffect, useState } from 'react'
import { apiOrderDetail } from '../../api';
import { Link } from 'react-router-dom';
import './index.css';
import ModalReviewProduct from '../ModalReviewProduct';
const OrderDetail = () => {
    const [listProducts, setListProducts] = useState([]);
    const [idOrder, setIdOrder] = useState("");
    const [showModal, setShowModal] = useState(false);
    const [isComment, setIsComment] = useState(false);
    const fetchGetProductInOrderDetail = async () => {
        await apiOrderDetail.fetchGetAllProductInOrderDetail()
            .then((res) => { setListProducts(res.data) })
            .catch((err) => { console.log(err) })
    }

    const splitString = (st) => {
        if (st.length !== 0) {
            if (st.length > 35) {
                return st.substring(0, 33) + '...';
            }
            else {
                return st;
            }
        }
    }

    const handleShowReview = (id) => {
        setIdOrder(id);
        handleShowModal();
    }
    const handleCloseModal = () => {
        setShowModal(false);
    }
    const handleShowModal = () => {
        setShowModal(true);
    }
    const handleCommented = () => {
        setIsComment(!isComment);
    }
    const displayProducts = (listProducts) => {
        if (listProducts.length > 0) {
            return listProducts.map((item, index) => {
                return (
                    <div className="row mt-5 mb-5" key={`listProducts-${index}`}>
                        <div className="col-md-10">
                            <div className="content d-flex" >
                                <div className="content-img">
                                    <img src={item.imgName} alt={item.productName} />
                                </div>
                                <div className="content-product">
                                    <div>{splitString(item.productName)}</div>
                                    <div>{item.colorName},{item.sizeName}</div>
                                    <div>x{item.quantity}</div>
                                </div>
                            </div>
                        </div>
                        <div className="col-md-2 d-flex align-items-center justify-content-around">
                            <div className='priceOrigin'>{item.price}</div>
                        </div>
                        <div className='total-price'>
                            <span className='total-price-name'>Tổng số tiền:</span> {item.totalPrice}
                        </div>
                        {item?.ratingId ? (<>
                            <div className='total-price'>
                                <Link to={`/productdetail/${item.productId}`} className='buy-again mt-3'>Mua lại</Link>
                            </div>
                        </>) :
                            (<>
                                <div className='total-price'>
                                    <button onClick={() => { handleShowReview(item.id) }} className='btnReviewProducts mt-3'>Đánh giá</button>
                                </div>

                            </>)}

                    </div>
                );
            });
        }
        else {
            return (<></>);
        }
    }


    useEffect(() => {
        fetchGetProductInOrderDetail();
    }, [isComment]);

    return (
        <div className='container'>
            {displayProducts(listProducts)}
            <ModalReviewProduct idOrder={idOrder} handleCloseModal={handleCloseModal} showModal={showModal} handleCommented={handleCommented} />
        </div>
    )
}

export default OrderDetail;