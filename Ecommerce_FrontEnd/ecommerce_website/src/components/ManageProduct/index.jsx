import React, { useEffect, useState } from 'react';
import { useSearchParams } from 'react-router-dom';
import ReactPaginate from 'react-paginate';
import { apiAdmin, apiCategory, apiProduct } from '../../api';
import { Link, useNavigate } from 'react-router-dom';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faMagnifyingGlass } from '@fortawesome/free-solid-svg-icons';
import ReactSwitch from "react-switch";
import ModalConfirm from '../ModalConfirm';
import './index.css';
const ManageProduct = () => {
    const navigate = useNavigate();
    const [searchParams, setSearchParams] = useSearchParams();
    const [lstCategory, setLstCategory] = useState([]);
    const [pageCount, setPageCount] = useState(4);
    const [itemOffset, setItemOffset] = useState("4");
    const [detailProduct, setDetailProduct] = useState({});
    const [itemCategory, setItemCategory] = useState("all");
    const [lstProduct, setLstProduct] = useState([]);
    const [showModalConfirm, setShowModalConfirm] = useState(false);
    const [itemPrice,setItemPrice]=useState("hightToLow");
    const fetchGetCategory = () => {
        apiCategory.fetchCategory().then((res) => {
            setLstCategory(res.data);
        }).catch((err) => { console.log(err); })
    }
    const fetchGetListProduct = async (searchKey, page, offset) => {
        await apiProduct.fetchGetEntireProduct(searchKey, page, offset).then((res) => {
            setPageCount(res.data.dataResponse.totalPage);
            setLstProduct(res.data.dataResponse.listProduct);
        }).catch((err) => { console.log('fetchGetListProduct failed') });
    };

    const handleChangeSelect = (event) => { setItemOffset(event.target.value); }
    const handleChangeSelectCategory = (event) => { setItemCategory(event.target.value); }
    const handleChangeSelectPrice = (event) => { setItemPrice(event.target.value); }
    const handlePageClick = (event) => {
        const { selected } = event;
        setSearchParams({ page: selected });
        setPageCount(selected);
    }

    const handleShowModalConfirm = () => { setShowModalConfirm(true); }
    const handleCloseModalConfirm = () => { setShowModalConfirm(false); }

    const fetchStatusProduct = (product) => {

        apiAdmin.fetchUpdateStatusProduct(product.id).then((res) => {
            if (res.data.code === '202') {
                let indexStatus = lstProduct.findIndex((item) => item.id === product.id);
                if (indexStatus === -1) {
                    return;
                }
                let tempList = [...lstProduct];
                tempList[indexStatus].statusProduct = tempList[indexStatus].statusProduct === 0 ? 1 : 0;
                setLstProduct(tempList);
                handleCloseModalConfirm();
            }
        }).catch((err) => console.log(err));
    }

    const handleChangeStatus = (item) => {
        setDetailProduct(item);
        handleShowModalConfirm();
    }
    const handleDisplayProduct = (lstProduct) => {
        if (lstProduct.length) {
            return lstProduct.map((item, index) => {
                return (
                    <div className='row mt-5' key={`table-user-${index}`} >
                        <div className="col-md-1">{index + 1}</div>
                        <div className="col-md-3">
                            <Link to={`/manage-detail-product/${item.id}`} className='managerDetailProduct' key={`table-user-${index}`}>
                                <img src={item?.nameImg} alt={item?.nameProduct} className='manager-img' />
                            </Link>
                        </div>
                        <div className="col-md-3">
                            <Link to={`/manage-detail-product/${item.id}`} className='managerDetailProduct' key={`table-user-${index}`}>
                                {item?.nameProduct}
                            </Link>
                        </div>
                        <div className="col-md-3">{item?.price}</div>
                        <div className="col-md-1">
                            <ReactSwitch checked={item?.statusProduct === 0 ? true : false} onChange={() => { handleChangeStatus(item) }} className="react-switch" />
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
        fetchGetCategory();
    }, []);


    useEffect(() => {
        let page = searchParams.get('page') ? searchParams.get('page') : 0;
        let searchKey = searchParams.get('searchKey');

        fetchGetListProduct(searchKey, page, itemOffset);
    }, [pageCount, itemOffset]);

    return (
        <div>
            <h1>Manage Product</h1>
            <form className="d-flex formSearchManger mt-5" >
                <input className="form-control me-2 navbarSearchInputManager" type="search" placeholder="Search" aria-label="Search" />
                <div className='containIconSearchManager'>
                    <FontAwesomeIcon icon={faMagnifyingGlass} id="iconSearchManager" />
                </div>
            </form>
            <div className='d-flex mt-5' >
             
                    <select className="form-select" value={itemCategory} onChange={handleChangeSelectCategory} style={{ width: '15%', marginLeft: '20px' }}>
                        <option defaultValue={'allCategory'}>All categories</option>
                        {lstCategory.length > 0 ? (
                            lstCategory.map((item, index) => {
                                return (<option value={item.id} key={`cate-${index}`}>{item.name}</option>)
                            })
                        ) : (<></>)}
                    </select>

                    <select className="form-select" value={itemPrice} onChange={handleChangeSelectPrice} style={{ width: '18%', marginLeft: '20px' }}>
                        <option defaultValue={'lowToHight'}>Price: Lowest to Highest</option>
                        <option defaultValue={'hightToLow'}>Price: Highest to Lowest</option>
                    </select>
    

            </div>
            <div className='d-flex justify-content-between mt-5 mb-3'>
                <div>
                    <button className='btn btn-success' onClick={() => { navigate('/create-product') }}>Create Product</button>
                </div>
                <div className='d-flex' >
                    <label className='mb-3'>Items per Page</label>
                    <select className="form-select" value={itemOffset} onChange={handleChangeSelect} style={{ width: '35%', textAlign: 'center', marginLeft: '20px' }}>
                        <option defaultValue={5}>5</option>
                        <option value={10}>10</option>
                        <option value={20}>20</option>
                    </select>
                </div>
            </div>

            <div className="row">
                <div className="col-md-1">#</div>
                <div className="col-md-3">Image</div>
                <div className="col-md-3">Name</div>
                <div className="col-md-3">Price</div>
                <div className="col-md-1">Status</div>
            </div>
            {handleDisplayProduct(lstProduct)}

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
            <ModalConfirm title={`Are you sure to do it?`} handleCloseModal={handleCloseModalConfirm}
                showModal={showModalConfirm} handleWork={fetchStatusProduct} item={detailProduct}
            />
        </div>

    )
}

export default ManageProduct;

