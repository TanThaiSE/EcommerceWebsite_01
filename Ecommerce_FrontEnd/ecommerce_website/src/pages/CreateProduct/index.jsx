import React from 'react'
import 'bootstrap/dist/css/bootstrap.min.css';
import 'bootstrap/dist/js/bootstrap.bundle.min.js';
import './index.css';
import NavBarAdmin from '../../components/Navbars/NavBarAdmin';
import NavBarInfoAdmin from '../../components/Navbars/NavBarInfoAdmin';
import AddProduct from '../../components/Products/AddProduct';

const CreateProduct = () => {
    return (
        <div className='container-fluid'>
            <div className="row">
                <div className="col-md-2">
                    <NavBarAdmin type={2}/>
                </div>
                <div className="col-md-10">
                    <NavBarInfoAdmin/>
                    <div>
                        <AddProduct/>
                    </div>
                </div>
            </div>
            
        </div>

    )
}

export default CreateProduct;