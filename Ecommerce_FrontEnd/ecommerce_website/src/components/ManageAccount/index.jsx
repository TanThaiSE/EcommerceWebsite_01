import React, { useEffect, useState } from 'react';
import ReactPaginate from 'react-paginate';
import { useSearchParams } from 'react-router-dom';
import { apiAdmin, apiProfile } from '../../api';
import ModalInfoUser from '../ModalInfoUser';
import './index.css';
import ModalConfirm from '../ModalConfirm';

const ManagerAccount = () => {
  const [searchParams, setSearchParams] = useSearchParams();
  const [lstUsers, setLstUsers] = useState([]);
  const [pageCount, setPageCount] = useState(4);
  const [itemOffset, setItemOffset] = useState(5);
  const [detailUser, setDetailUser] = useState({});
  const [showModal, setShowModal] = useState(false);
  const [showModalConfirm, setShowModalConfirm] = useState(false);
  
  const fetchGetUsers = (page, offset) => {
    apiAdmin.fetchGetUsers(page, offset).then((res) => {
      setPageCount(res.data.dataResponse.totalPage);
      setLstUsers(res.data.dataResponse.listUser);
    })
      .catch((err) => {
        console.log(err);
      })
  }

  const handleShowModal = () => { setShowModal(true); }
  const handleCloseModal = () => { setShowModal(false); }
  const handleShowModalConfirm = () => { setShowModalConfirm(true); }
  const handleCloseModalConfirm = () => { setShowModalConfirm(false); }

  const handlePageClick = (event) => {
    const { selected } = event;
    setSearchParams({ page: selected });
    setPageCount(selected);
  }

  const handleChangeSelect = (event) => {
    setItemOffset(event.target.value);
  }
  const handleShowDetailInfo = (idCustomer) => {
    apiProfile.fetchGetDetailProfile(idCustomer)
      .then((res) => {
        setDetailUser(res.data.dataResponse);
        handleShowModal();
      })
      .catch((err) => {
        console.log(err);
      });
  }
  
    const fetchUpdateBlockedUser = (account) => {
    let idCustomer=account.accountsProfiles.id;
    let id=account.id;
    apiAdmin.fetchUpdateBlockedUser(idCustomer)
      .then((res) => {
        if (res.data.code === '202') {
          let indexBlocked = lstUsers.findIndex((item) => item.id === id);
          if (indexBlocked === -1) {
            return;
          }
          let tempList = [...lstUsers];
          tempList[indexBlocked].accountsProfiles.isBlocked = tempList[indexBlocked].accountsProfiles.isBlocked === 0 ? 1 : 0;
          setLstUsers(tempList);
          handleCloseModalConfirm();
        }
      })
      .catch((err) => console.log(err));
  }
const handleBlocked=(item)=>{
  setDetailUser(item);
  handleShowModalConfirm();
}
  const handleDisplayUsers = (lstUsers) => {
    if (lstUsers.length) {
      return lstUsers.map((item, index) => {
        return (
          <tr key={`table-user-${index}`}>
            <td>{item.name}</td>
            <td>{item.accountsProfiles.email}</td>
            <td>{item.accountsProfiles.phone}</td>
            <td>{item.accountsProfiles.roles.name === 'ROLE_USER' ? ('CUSTOMER') : ('ADMIN')}</td>
            <td>
              <div className='d-flex'>
                <button type="button" className="btn btn-outline-primary mx-3" onClick={() => { handleShowDetailInfo(item.accountsProfiles.id) }}>Detail</button>
                {item.accountsProfiles.isBlocked === 0 ?
                  (<button type="button" className="btn btn-outline-success" onClick={() => { handleBlocked(item) }}>Active</button>) :
                  (<button type="button" className="btn btn-outline-danger" onClick={() => { handleBlocked(item) }}>Block</button>)}
              </div>

            </td>
          </tr>
        )
      })
    }
    else {
      return (<></>);
    }
  }
  useEffect(() => {
    let page = searchParams.get('page') ? searchParams.get('page') : 0;
    fetchGetUsers(page, itemOffset);
  }, [pageCount, itemOffset]);
  return (
    <div>
      <h1>Manage Account</h1>
      <div className='d-flex justify-content-end mt-5'>
        <label className='mb-3'>Items per Page</label>
        <select className="form-select" value={itemOffset} onChange={handleChangeSelect} style={{ width: '15%',textAlign:'center', marginLeft:'20px' }}>
          <option defaultValue={5}>5</option>
          <option value={10}>10</option>
          <option value={20}>20</option>
        </select>
      </div>

      <table className="table table-striped mt-4">
        <thead>
          <tr>
            <th>Full Name</th>
            <th>Email</th>
            <th>Phone Number</th>
            <th>Role</th>
            <th>Option</th>
          </tr>
        </thead>
        <tbody>
          {handleDisplayUsers(lstUsers)}
        </tbody>
      </table>

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
      <ModalInfoUser detailUser={detailUser} handleCloseModalAccount={handleCloseModal} showModalAccount={showModal} />
      <ModalConfirm  title={`Are you sure to do it?`} handleCloseModal={handleCloseModalConfirm} 
        showModal={showModalConfirm} handleWork={fetchUpdateBlockedUser} item={detailUser}
        />
    </div>
  )
}

export default ManagerAccount;