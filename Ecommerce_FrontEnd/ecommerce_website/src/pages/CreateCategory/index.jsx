import React from 'react'
import 'bootstrap/dist/css/bootstrap.min.css';
import 'bootstrap/dist/js/bootstrap.bundle.min.js';
import './index.css';
import NavBarAdmin from '../../components/Navbars/NavBarAdmin';
import NavBarInfoAdmin from '../../components/Navbars/NavBarInfoAdmin';
import AddCategory from '../../components/Categories/AddCategory';

const CreateCategory = () => {
    return (
        <div className='container-fluid'>
            <div className="row">
                <div className="col-md-2">
                    <NavBarAdmin type={1}/>
                </div>
                <div className="col-md-10">
                    <NavBarInfoAdmin/>
                    <div>
                        <AddCategory/>
                    </div>
                </div>
            </div>
            
        </div>

    )
}

export default CreateCategory;