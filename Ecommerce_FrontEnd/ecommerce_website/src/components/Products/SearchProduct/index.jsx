import React, { useEffect, useState } from 'react';
import { useSearchParams } from 'react-router-dom';
import ReactPaginate from 'react-paginate';
import { apiProduct } from '../../../api';
import ProductItem from '../ProductItem';
import './index.css';

const SearchProduct = ({idCategory}) => {
  const [lstProduct, setLstProduct] = useState([]);
  const [searchParams, setSearchParams] = useSearchParams();
  const [pageCount, setPageCount] = useState(4);
  const [itemOffset, setItemOffset] = useState("5");
  const [itemPrice, setItemPrice] = useState("desc");

  const fetchGetListProduct = async (searchKey, sortPrice, page, offset) => {
    await apiProduct.fetchGetEntireProduct(searchKey, sortPrice, page, offset).then((res) => {
        setPageCount(res.data.dataResponse.totalPage);
        setLstProduct(res.data.dataResponse.listProduct);
    }).catch((err) => { setLstProduct([]) });
};

  const displayListProduct = (lstProduct) => {
    if (lstProduct.length !== 0) {
      return lstProduct.map((item, index) => {
        return (
          <div className="col-3 mb-3 mt-3" key={index} >
            <ProductItem productItem={item} />
          </div>
        )
      });
    }
    else {
      return <></>
    }
  }
  const handleChangeSelect = (event) => { setItemOffset(event.target.value); }
  const handleChangeSelectPrice = (event) => {
    setSearchParams({ sortByPrice: event.target.value, page: 0 });
    setItemPrice(event.target.value);
  }

  const handlePageClick = (event) => {
    const { selected } = event;
    let sortByPrice = searchParams.get('sortByPrice');
    if (sortByPrice && sortByPrice.length) {
      setSearchParams({ sortByPrice: sortByPrice, page: selected });
    }
    else {
      setSearchParams({ page: selected });
    }
    setPageCount(selected);
  }


  useEffect(() => {
    let page = searchParams.get('page') ? searchParams.get('page') : 0;
    let searchKey = searchParams.get('keyword');
    let sortPrice = searchParams.get('sortByPrice') ? searchParams.get('sortByPrice') : 'desc'
    fetchGetListProduct(searchKey, sortPrice, page, itemOffset);
}, [pageCount, itemOffset, itemPrice]);

  return (
    <div className='container'>
      <div className='d-flex justify-content-between mt-5 mb-3'>
        <select className="form-select" value={itemPrice} onChange={handleChangeSelectPrice} style={{ width: '18%', marginLeft: '20px' }}>
          <option value={'desc'}>Price: Highest to Lowest</option>
          <option value={'asc'}>Price: Lowest to Highest</option>
        </select>
        <div className='d-flex' >
          <label className='mb-3'>Items per Page</label>
          <select className="form-select" value={itemOffset} onChange={handleChangeSelect} style={{ width: '35%', textAlign: 'center', marginLeft: '20px' }}>
            <option value={5}>5</option>
            <option value={10}>10</option>
            <option value={20}>20</option>
          </select>
        </div>
      </div>
      <div className='row'>
        {displayListProduct(lstProduct)}
      </div>
      {lstProduct.length !== 0 ? (
        <ReactPaginate
          breakLabel='...'
          nextLabel='>'
          onPageChange={handlePageClick}
          pageRangeDisplayed={2}
          pageCount={pageCount}
          previousLabel='<'
          renderOnZeroPageCount={null}
          containerClassName='pagination'
          pageLinkClassName='page-num'
          previousLinkClassName='page-num'
          nextLinkClassName='page-num'
          activeLinkClassName='active'
        />

      ) : (<></>)}

    </div>
  )
}

export default SearchProduct;