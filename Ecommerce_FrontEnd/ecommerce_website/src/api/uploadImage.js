import axios from "axios";

export const uploadImage = async (file) => {
    const formData = new FormData();
    formData.append("file", file);
    formData.append("upload_preset", "qxlj7ldk");
    try {
        let result = await axios.post("https://api.cloudinary.com/v1_1/dwolphrup/image/upload", formData);
        return result;
    } catch (err) {
        return err;
    }
};
