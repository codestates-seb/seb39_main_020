import axios from "axios";

const axiosConfig = {

}

const aget = (url, data, successFn, failFn) => {
    return axios({
        ...axiosConfig, 
        url,
        method : "get",
        params: data
    }).then((res) => {
        (!!successFn) && successFn();
        return res.data;
    }).catch((err) => {
        (!!failFn) && failFn();
        return err;
    });
}

const apost = (url, data, successFn, failFn) => {
    return axios({
        ...axiosConfig, 
        url,
        method : "post",
        data
    }).then((res) => {
        (!!successFn) && successFn();
        return res.data;
    }).catch((err) => {
        (!!failFn) && failFn();
        return err;
    });
}

const aput = (url, data, successFn, failFn) => {
    return axios({
        ...axiosConfig, 
        url,
        method : "put",
        data
    }).then((res) => {
        console.log(res)
        (!!successFn) && successFn();
        return res.data;
    }).catch((err) => {
        (!!failFn) && failFn();
        return err;
    });
}

const adelete = (url, data, successFn, failFn) => {
    return axios({
        ...axiosConfig, 
        url,
        method : "delete",
        data
    }).then((res) => {
        (!!successFn) && successFn();
        return res.data;
    }).catch((err) => {
        (!!failFn) && failFn();
        return err;
    });
}


export {aget, apost, aput, adelete};