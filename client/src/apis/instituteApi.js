import { adelete, aget, apost, aput } from "../configs/axiosConfig";

const targetBE = process.env.REACT_APP_BACKEND_URI+'/main/institutes'; 

const getMainInstitutesApi = () => {
    return aget(`${targetBE}/main`);
}

const getMainCoursesApi = () => {
    return aget(`${targetBE}/main/courses`);
}

const getInstitutesApi = (payload) => { 
    return aget(`${targetBE}`,payload);
};

const getInstituteApi = (id) => {
    return aget(`${targetBE}/${id}`)
}

const getCoursesApi = (id) => {
    return aget(`${targetBE}/${id}/courses`)
}

const getReviewsApi = (id) => {
    return aget(`${targetBE}/${id}/reviews`)
}

const postInstituteApi = (payload) => {
    return apost(`${targetBE}`,payload)
}

const putInstituteApi = (id, payload) => {
    delete payload.id;
    return aput(`${targetBE}/${id}`,payload)
}

const deleteInstituteApi = (id) => {
    return adelete(`${targetBE}/${id}`)
}

const postReviewApi = (id, payload) => {
    return apost(`${targetBE}/${id}/review`,payload)
}

export {getMainCoursesApi,getMainInstitutesApi, getInstitutesApi, getInstituteApi,postReviewApi, postInstituteApi,putInstituteApi, deleteInstituteApi,getCoursesApi,getReviewsApi};