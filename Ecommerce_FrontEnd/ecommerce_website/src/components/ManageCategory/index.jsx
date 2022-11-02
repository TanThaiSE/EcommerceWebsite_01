import React, { useEffect, useState } from 'react';
import { useSearchParams } from 'react-router-dom';
import ReactPaginate from 'react-paginate';
import { apiAdmin } from '../../api';

const ManageCategory = () => {
    const [searchParams, setSearchParams] = useSearchParams();
    const [lstCategory, setLstCategory] = useState([]);
    const [pageCount, setPageCount] = useState(4);
    const [itemOffset, setItemOffset] = useState(4);
    const [detailCategory, setDetailCategory] = useState({});
    const [showModal, setShowModal] = useState(false);
    const [showModalConfirm, setShowModalConfirm] = useState(false);

    // const [imageSelected,setImageSelected]=useState("");
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
    const handleShowModalConfirm = () => { setShowModalConfirm(true); }
    const handleCloseModalConfirm = () => { setShowModalConfirm(false); }

    const handleShowDetailInfo = (idCustomer) => {
        // apiProfile.fetchGetDetailProfile(idCustomer)
        //   .then((res) => {
        //     setDetailUser(res.data.dataResponse);
        //     handleShowModal();
        //   })
        //   .catch((err) => {
        //     console.log(err);
        //   });
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
                                <button type="button" className="btn btn-primary">Detail</button>
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
    // const uploadImage=()=>{
    //     // console.log(files[0]);
    //     const formData=new FormData();
    //     formData.append("file",imageSelected);
    //     formData.append("upload_preset", "qxlj7ldk");
    //     axios.post("https://api.cloudinary.com/v1_1/dwolphrup/image/upload",formData).then((res)=>{
    //         console.log(res);
    //     }).catch((err)=>console.log(err))

    // }

    useEffect(() => {
        let page = searchParams.get('page') ? searchParams.get('page') : 0;
        fetchGetCategory(page, itemOffset);
    }, [pageCount, itemOffset]);

    return (
        <div>
            <h1>Manage Category</h1>
            <div className='d-flex justify-content-end mt-5'>
                <label className='mb-3'>Items per Page</label>
                <select className="form-select" value={itemOffset} onChange={handleChangeSelect} style={{ width: '15%', textAlign: 'center', marginLeft: '20px' }}>
                    <option defaultValue={4}>4</option>
                    <option value={8}>8</option>
                    <option value={16}>16</option>
                </select>
            </div>

            <div className="row">
                {handleDisplayCategories(lstCategory)}
                {/* <input type="file" onChange={(e)=>{setImageSelected(e.target.files[0])}}/> */}
                {/* <button onClick={uploadImage}>upload image</button> */}
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

        </div>
    )
}

export default ManageCategory;