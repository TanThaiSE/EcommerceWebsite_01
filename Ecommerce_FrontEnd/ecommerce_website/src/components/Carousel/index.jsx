import React from 'react';
import './index.css';
const CarouselBanner = () => {
    return (
        <div>
        
            <div id="carouselExampleIndicators" className="carousel slide container mt-4" data-bs-ride="carousel">
                <div className="carousel-indicators">
                    <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to={0} className="active" aria-current="true" aria-label="Slide 1" />
                    <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to={1} aria-label="Slide 2"/>
                    <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to={2} aria-label="Slide 3"/>
                    <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to={3} aria-label="Slide 4"/>
                </div>
                <div className="carousel-inner">
                    <div className="carousel-item active">
                        <img src="/image/image-banner-1.png" className="d-block carouselImg" alt="..." />
                    </div>
                    <div className="carousel-item">
                        <img src="/image/image-banner-2.png" className="d-block carouselImg" alt="..." />
                    </div>
                    <div className="carousel-item">
                        <img src="/image/image-banner-3.png" className="d-block carouselImg" alt="..." />
                    </div>
                    <div className="carousel-item">
                        <img src="/image/image-banner-4.png" className="d-block carouselImg" alt="..." />
                    </div>
                </div>
                <button className="carousel-control-prev" type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide="prev">
                    <span className="carousel-control-prev-icon" aria-hidden="true" />
                    <span className="visually-hidden">Previous</span>
                </button>
                <button className="carousel-control-next" type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide="next">
                    <span className="carousel-control-next-icon" aria-hidden="true" />
                    <span className="visually-hidden">Next</span>
                </button>
            </div>

        </div>
    )
}

export default CarouselBanner;