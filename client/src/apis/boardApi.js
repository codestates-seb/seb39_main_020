import { adelete, aget, apost, aput } from "../configs/axiosConfig";

const targetBE = process.env.REACT_APP_BACKEND_URI+'/main/community'; 

const getBoardsApi = (board,payload) => { 
    return aget(`${targetBE}/${board}`,payload);
};

const getBoardMainApi = () => { 
    return aget(`${targetBE}/boardMain`);
};

const getBoardApi = (board,id) => {
    return aget(`${targetBE}/${board}/${id}`)
}

const getMainboardsApi = () => {
    return aget(`${targetBE}/main`);
}

const postBoardApi = (board,payload) => {
    return apost(`${targetBE}/${board}`,payload)
}

const putBoardApi = (board, id, payload) => {
    delete payload.id;
    console.log(id,payload)
    return aput(`${targetBE}/${board}/${id}`,payload)
}

const deleteBoardApi = (id) => {
    return adelete(`${targetBE}/${id}`)
}

const getCommentsApi = (board,id) => { 
    return aget(`${targetBE}/${board}/${id}/comments`);
};

const postCommentsApi = (board,id,payload) => {
    return apost(`${targetBE}/${board}/${id}/comments`,payload);
}

export {getBoardsApi, getBoardApi, getMainboardsApi,getBoardMainApi, postBoardApi, putBoardApi, deleteBoardApi, getCommentsApi, postCommentsApi };