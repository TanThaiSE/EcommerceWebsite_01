import React, { useEffect, useState } from 'react';
import { useSearchParams } from 'react-router-dom';
import ReactPaginate from 'react-paginate';
import { apiCategory } from '../../../api';
import ModalInfoCategory from '../../Modals/ModalInfoCategory';
import { useNavigate } from 'react-router-dom';
import ModalConfirm from '../../Modals/ModalConfirm';
const ManageCategory = () => {
    const navigate = useNavigate();
    const [searchParams, setSearchParams] = useSearchParams();
    const [lstCategory, setLstCategory] = useState([]);
    const [pageCount, setPageCount] = useState(4);
    const [itemOffset, setItemOffset] = useState(4);
    const [detailCategory, setDetailCategory] = useState({});
    const [showModal, setShowModal] = useState(false);
    const [showModalConfirm, setShowModalConfirm] = useState(false);
    const fetchGetCategory = (page, offset) => {
        apiCategory.fetchCategoryManger(page, offset).then((res) => {
            setPageCount(res.data.dataResponse.totalPage);
            setLstCategory(res.data.dataResponse.listCategories);
        }).catch((err) => {
            console.log(err);
            setLstCategory([]);
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
    const handleShowModalConfirm = () => { setShowModalConfirm(true); }
    const handleCloseModalConfirm = () => { setShowModalConfirm(false); }
    const handleShowCategory = (item) => {
        setDetailCategory(item);
        handleShowModal();
    }
    const handleEditCategory = (data) => {
        apiCategory.fetchUpdateCategory(data)
            .then((res) => {
                let tempLstCategory = [...lstCategory];
                let indexItem = tempLstCategory.findIndex((item) => item.id === data.id);
                tempLstCategory[indexItem] = data;
                setLstCategory(tempLstCategory);
                handleCloseModal();
            })
            .catch((err) => console.log(err))
    }

    const handleDeleteCategory = (item) => {
        setDetailCategory(item);
        handleShowModalConfirm();
    }

    const fetchDeleteEmptyCategory = (category) => {
        apiCategory.fetchDeleteEmptyCategory(category.id).then((res) => {
            if (res.data.code === '202') {
                let indexRemove = lstCategory.findIndex((item) => item.id === category.id);
                if (indexRemove === -1) {
                    return;
                }
                let tempList = [...lstCategory];
                tempList.splice(indexRemove, 1);
                setLstCategory(tempList);
                handleCloseModalConfirm();
            }
        }).catch((err) => console.log(err));
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
                                <div className='d-flex justify-content-between'>
                                    <button type="button" className="btn btn-success" onClick={() => { handleShowCategory(item) }}>Edit</button>
                                    {item.statusCategory === 0 ?
                                        (<button type="button" className="btn btn-danger" onClick={() => { handleDeleteCategory(item) }}>Delete</button>)
                                        : (<></>)}
                                </div>


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
            <h1>Manage Category</h1>
            <div className='d-flex justify-content-between mt-5 mb-3'>

                <div>
                    <button className='btn btn-success' onClick={() => { navigate('/create-category') }}>Create Category</button>
                </div>

                <div className='d-flex' >
                    <label className='mb-3'>Items per Page</label>
                    <select className="form-select" value={itemOffset} onChange={handleChangeSelect} style={{ width: '35%', textAlign: 'center', marginLeft: '20px' }}>
                        <option defaultValue={4}>4</option>
                        <option value={8}>8</option>
                        <option value={16}>16</option>
                    </select>
                </div>

            </div>


            <div className="row">
                {handleDisplayCategories(lstCategory)}

            </div>

            {lstCategory.length !== 0 ? (
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

            <ModalInfoCategory detailCategory={detailCategory} handleCloseModal={handleCloseModal} showModal={showModal} handleWork={handleEditCategory} />
            <ModalConfirm title={`Are you sure to do it?`} handleCloseModal={handleCloseModalConfirm} showModal={showModalConfirm} handleWork={fetchDeleteEmptyCategory} item={detailCategory}
            />
        </div>
    )
}

export default ManageCategory;

