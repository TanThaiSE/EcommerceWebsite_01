import React, { useEffect, useState } from 'react';
import './index.css';
import CategoryItem from '../CategoryItem';
import { apiCategory } from '../../api';
const Category = () => {
  const [lstCategories, setLstCategories] = useState([]);

  const fetchListCategories = async () => {
    await apiCategory.fetchCategory()
      .then((res) => setLstCategories(res.data))
      .catch((err) => { console.log('fetchListCategories failed') });
  };

  const displayListCategory = (lstCategories) => {
    if (lstCategories.length !== 0) {
      return lstCategories.map((item, index) => {
        return (
          <div className="col-2" key={index} >
            <CategoryItem cateItem={item} />
          </div>
        )
      });
    }
    else {
      return <></>
    }
  }

  useEffect(() => {
    fetchListCategories();
  }, []);

  return (
    <div className='container'>
      <div className='nameCategory'>
        <p>DANH MỤC</p> 
      </div>
    
      <div className='row'>
        {displayListCategory(lstCategories)}
      </div>
    </div>
  )
}

export default Category;