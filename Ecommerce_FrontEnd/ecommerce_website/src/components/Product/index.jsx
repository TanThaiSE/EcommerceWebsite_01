import React, { useEffect, useState } from 'react'
import { apiProduct } from '../../api';
import ProductItem from '../ProductItem';
import './index.css';

const Product = ({ idCategory }) => {
  const [lstProduct, setLstProduct] = useState([]);

  const fetchListProducts = async (idCategory) => {
    await apiProduct.fetchGetAllProduct(idCategory)
      .then((res) => setLstProduct(res.data))
      .catch((err) => { console.log('fetchListProducts failed') });
  };

  const displayListProduct = (lstProduct) => {
    if (lstProduct.length !== 0) {
      return lstProduct.map((item, index) => {
        return (
          <div className="col-3" key={index} >
            <ProductItem productItem={item} />
          </div>
        )
      });
    }
    else {
      return <></>
    }
  }

  useEffect(() => {
    fetchListProducts(idCategory);
  }, []);

  return (
    <div className='container'>
      Product
      <div className='row'>
        {displayListProduct(lstProduct)}
      </div>

    </div>
  )
}

export default Product