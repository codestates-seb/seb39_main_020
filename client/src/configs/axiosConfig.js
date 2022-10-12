import axios from "axios";

const axiosConfig = {
    headers:{Authorization:'token'}
}

const aget = (url, data, successFn, failFn, newProp) => {
    return axios({
        ...axiosConfig, 
        url,
        method : "get",
        params: data,
        ...newProp,    
    }).then((res) => {
        (!!successFn) && successFn();
        return res.data;
    }).catch((err) => {
        (!!failFn) && failFn();
        throw err.message;
    });
}

const apost = (url, data, successFn, failFn, newProp) => {
    return axios({
        ...axiosConfig, 
        url,
        method : "post",
        data,
        ...newProp
    }).then((res) => {
        (!!successFn) && successFn();
        return res.data;
    }).catch((err) => {
        (!!failFn) && failFn();
        throw err.message;
    });
}

const aput = (url, data, successFn, failFn, newProp) => {
    return axios({
        ...axiosConfig, 
        url,
        method : "patch",
        data,
        newProp
    }).then((res) => {
        (!!successFn) && successFn();
        return res.data;
    }).catch((err) => {
        (!!failFn) && failFn();
        throw err.message;
    });
}

const adelete = (url, data, successFn, failFn, newProp) => {
    return axios({
        ...axiosConfig, 
        url,
        method : "delete",
        data,
        newProp
    }).then((res) => {
        (!!successFn) && successFn();
        return res.data;
    }).catch((err) => {
        (!!failFn) && failFn();
        throw err.message;
    });
}


export {aget, apost, aput, adelete};