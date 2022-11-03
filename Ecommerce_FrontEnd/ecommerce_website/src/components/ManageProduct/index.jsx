import React, { useEffect, useState } from 'react';
import { useSearchParams } from 'react-router-dom';
import ReactPaginate from 'react-paginate';
import { apiAdmin } from '../../api';
import ModalInfoCategory from '../ModalInfoCategory';
import { Link, useNavigate } from 'react-router-dom';
const ManageProduct = () => {
    const navigate = useNavigate();
    const [searchParams, setSearchParams] = useSearchParams();
    const [lstCategory, setLstCategory] = useState([]);
    const [pageCount, setPageCount] = useState(4);
    const [itemOffset, setItemOffset] = useState(4);
    const [detailCategory, setDetailCategory] = useState({});
    const [showModal, setShowModal] = useState(false);

    const fetchGetCategory = (page, offset) => {
        apiAdmin.fetchCategory(page, offset).then((res) => {
            setPageCount(res.data.dataResponse.totalPage);
            setLstCategory(res.data.dataResponse.listCategories);
        }).catch((err) => {
            console.log(err);
        })
    }
    const handleChangeSelect = (event) => {
        setItemOffset(event.target.value);
    }
    const handlePageClick = (event) => {
        const { selected } = event;
        setSearchParams({ page: selected });
        setPageCount(selected);
    }
    const handleShowModal = () => { setShowModal(true); }
    const handleCloseModal = () => { setShowModal(false); }
    const handleShowCategory = (item) => {
        setDetailCategory(item);
        handleShowModal();
    }
    const handleEditCategory = (data) => {
        apiAdmin.fetchUpdateCategory(data)
            .then((res) => {
                let tempLstCategory = [...lstCategory];
                let indexItem = tempLstCategory.findIndex((item) => item.id === data.id);
                tempLstCategory[indexItem] = data;
                setLstCategory(tempLstCategory);
                handleCloseModal();
            })
            .catch((err) => console.log(err))
    }


    const handleDisplayCategories = (lstCategory) => {
        if (lstCategory.length) {
            return lstCategory.map((item, index) => {
                return (
                    <div className="col-md-3 mt-3 mb-4" key={`categories-${index}`}>
                        <div className="card" style={{ width: '18rem' }}>
                            <img src={item.image} className="card-img-top" alt={item.name} />
                            <div className="card-body">
                                <h5 className="card-title">{item.name}</h5>
                                <button type="button" className="btn btn-success" onClick={() => { handleShowCategory(item) }}>Edit</button>
                            </div>
                        </div>
                    </div>
                )
            })
        }
        else {
            return (<></>);
        }
    }


    useEffect(() => {
        let page = searchParams.get('page') ? searchParams.get('page') : 0;
        fetchGetCategory(page, itemOffset);
    }, [pageCount, itemOffset]);

    return (
        <div>
            <h1>Manage Product</h1>
            <div className='d-flex justify-content-between'>
            <div className='d-flex'>
                <label className='mb-3'>Items per Page</label>
                <select className="form-select" value={itemOffset} onChange={handleChangeSelect} style={{ width: '35%', textAlign: 'center', marginLeft: '20px' }}>
                    <option defaultValue={4}>4</option>
                    <option value={8}>8</option>
                    <option value={16}>16</option>
                </select>
            </div>
            <div>
                <button className='btn btn-success' onClick={()=>{navigate('/create-category')}}>Create Product</button>
            </div>
            </div>


            <div className="row">
                {handleDisplayCategories(lstCategory)}

            </div>


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
            <ModalInfoCategory detailCategory={detailCategory} handleCloseModal={handleCloseModal} showModal={showModal} handleWork={handleEditCategory} />
        </div>
    )
}

export default ManageProduct;

