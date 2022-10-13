import React from 'react';
import { Link } from 'react-router-dom';
import './index.css';
const CategoryItem = ({ cateItem }) => {
  return (
    <>
      <Link to={`/category/${cateItem.id}`} className='categoryLink'>
        <div className='categoryItem'>
          <img src={cateItem.image} alt={cateItem.name} className='categoryItemImg' />
          <p className='categoryItemName'>{cateItem.name}</p>
        </div>
      </Link>
    </>
  )
}

export default CategoryItem;