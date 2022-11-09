import React from 'react';
import { Link } from 'react-router-dom';
import './index.css';
import StarRatings from 'react-star-ratings';
const ProductItem = ({ productItem }) => {
    const splitString = (st) => {
        if (st.length !== 0) {
            if (st.length > 40) {
                return st.substring(0, 60) + '...';
            }
            else {
                return st;
            }
        }
    }
    const displayRateByStar=(numbeRating)=>{
        return <StarRatings
                rating={numbeRating}
                starRatedColor='#ffce3d'
                numberOfStars={5}
                starDimension="16px"
                starSpacing="2px"
        />
    }
    return (
        <>
            <Link to={`/productdetail/${productItem.id}`} style={{ textDecoration: 'none', color: 'black' }}>
                <div className="card" style={{ width: '18rem' }}>
                    <img src={productItem.nameImg} className="card-img-top" alt={productItem.nameProduct} />
                    <div className="card-body">
                        <p>{splitString(productItem.nameProduct)}</p>
                        <p className='productPrice'>{productItem.price}</p>
                        <div className='d-flex'>
                            {displayRateByStar(productItem.rate)}
                            <p className='productBought'>Đã bán {productItem.number_buy}</p>
                        </div>
                    </div>
                </div>

            </Link>





        </>
    )
}

export default ProductItem;