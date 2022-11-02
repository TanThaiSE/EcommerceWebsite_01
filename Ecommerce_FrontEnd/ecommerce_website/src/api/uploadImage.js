import axios from "axios";

export const uploadImage = async (file) => {
    if (!file) throw Error("No file is chosen");

    if (file.size === 0 || file.type.split("/")[0] !== "image" || file.size > 1048576)
        throw Error("Your file is invalid");

    const formData = new FormData();
    formData.append("file", file);
    formData.append("upload_preset", "qxlj7ldk");

    try {
        let result = await axios.post("https://api.cloudinary.com/v1_1/dwolphrup/image/upload", formData);
        return {
            fileName: file.name,
            publicUrl: result.data.url,
            createdAt: result.data.created_at,
            originalFileName: result.data.original_filename,
            fileFormat: result.data.format,
            publicID: result.data.public_id,
        };
    } catch (err) {
        return err;
    }
};
