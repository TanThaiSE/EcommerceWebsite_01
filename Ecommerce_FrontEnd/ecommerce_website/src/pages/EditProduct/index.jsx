import React from 'react'
import 'bootstrap/dist/css/bootstrap.min.css';
import 'bootstrap/dist/js/bootstrap.bundle.min.js';
import './index.css';
import NavBarAdmin from '../../components/Navbars/NavBarAdmin';
import NavBarInfoAdmin from '../../components/Navbars/NavBarInfoAdmin';
import ManageDetailProduct from '../../components/Products/ManageDetailProduct';
import { useParams } from 'react-router';
const EditProduct = () => {
    const {idProduct}=useParams();
    return (
        <div className='container-fluid'>
            <div className="row">
                <div className="col-md-2">
                    <NavBarAdmin/>
                </div>
                <div className="col-md-10">
                    <NavBarInfoAdmin/>
                    <div>
                        <ManageDetailProduct idProduct={idProduct}/>
                    </div>
                </div>
            </div>
            
        </div>

    )
}

export default EditProduct;