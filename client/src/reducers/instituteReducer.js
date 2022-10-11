import { createAction, createAsyncThunk, createSlice } from '@reduxjs/toolkit'
import {getInstitutesApi, getInstituteApi, postInstituteApi,putInstituteApi, deleteInstituteApi, getCoursesApi, getReviewsApi, getMainCoursesApi, getMainInstitutesApi, postReviewApi} from '../apis/instituteApi'

// actions 
export const fetchMainInstitutes = createAsyncThunk('INSTITUTE/MAIN', () => {
  return getMainInstitutesApi();
});


export const fetchMainCourses = createAsyncThunk('INSTITUTE/MAIN/COURSES', () => {
  return getMainCoursesApi();
});

export const fetchInstitutes = createAsyncThunk('INSTITUTE/INSTITUTES', (payload) => {
  return getInstitutesApi(payload);
});

export const fetchInstitute = createAsyncThunk('INSTITUTE/INSTITUTE', (payload) => {
  const id = (!!payload && typeof payload === 'object') ? payload.id : payload;
  return getInstituteApi(id);
});

export const fetchCourses = createAsyncThunk('INSTITUTE/COURSES', (payload) => {
  const id = (!!payload && typeof payload === 'object') ? payload.id : payload;
  return getCoursesApi(id);
});


export const fetchReviews = createAsyncThunk('INSTITUTE/REVIEWS', (payload) => {
  const id = (!!payload && typeof payload === 'object') ? payload.id : payload;
  return getReviewsApi(id);
});

export const postInstitute = createAsyncThunk('INSTITUTE/CREATE_INSTITUTE', (payload) => {
  return !!(payload.data) && postInstituteApi(payload.data).then(() =>{
    (!!payload.successFn) && payload.successFn();
    return payload;
  });
});

export const putInstitute = createAsyncThunk('INSTITUTE/UPDATE_INSTITUTE', (payload) => {
  const id = (!!payload?.data && typeof payload.data === 'object') ? payload?.data?.id : 0;
  return !!(payload) && putInstituteApi(id, payload.data).then(() => {
    (!!payload.successFn) && payload.successFn();
    return payload;
  });
});

export const deleteInstitute = createAsyncThunk('FAQ/DELETE_Institute', (payload) => {
  const id = (!!payload && typeof payload === 'object') ? payload.id : payload;
  return deleteInstituteApi(id);
});

export const postReview = createAsyncThunk('INSTITUTE/CREATE_REVIEW', (payload) => {
  return !!(payload.data) && postReviewApi(payload.id, payload.data).then(() =>{
    (!!payload.successFn) && payload.successFn();
    return payload;
  });
});

export const initList = createAction("INIT_LIST");
export const initView = createAction("INIT_VIEW");
export const initMainCourses = createAction("INIT_MAIN_COURSES");
export const initMainInstitutes = createAction("INIT_MAIN_INSTITUTES");
export const initCourses = createAction("INIT_COURSES");
export const initReviews = createAction("INIT_REVIEWS");
// init states
const initialState = {
  list: {
    data:[],
    pageInfo:{}
  },
  view: {
		instituteId : "",
		name : "",
		description : "",
		employee : 0,
		location : "",
		studyType: "",
		score : 0,
		homepage : "",
		createdDate : "",
		modifiedDate : "",
  },
  mainInstitutes: [],
  mainCourses: [],
  courses: [],
  reviews: [],
  loading : false,
  error: ""
}

const errorState = (err) => {
  return {...initialState, error: err} 
};

// slice
export const instituteSlice = createSlice({
  name: 'institute',
  initialState,
  reducers: {

  },
  extraReducers: {
    [initList] : (state) => {
      state.list = initialState.list;
    },
    [initView] : (state) => {
      state.view = initialState.view;
      state.courses = initialState.courses;
      state.reviews = initialState.reviews;
    },
    [initMainInstitutes] : (state) => {
      state.mainInstitutes = initialState.mainInstitutes;
    },
    [initMainCourses] : (state) => {
      state.mainCourses = initialState.mainCourses;
    },
    [initCourses] : (state) => {
      state.courses = initialState.courses;
    },
    [initReviews] : (state) => {
      state.reviews = initialState.reviews;
    },
    [fetchInstitutes.pending] : (state) => {
      state.list= {};
      state.loading = true;
      state.error = "";
    },
    [fetchInstitutes.fulfilled] : (state, action) => {
      state.list= action.payload;
      state.loading = false;
      state.error = "";
    },
    [fetchInstitutes.rejected] : (_, action) => {
      errorState(action.payload)
    },
    [fetchInstitute.pending] : (state) => {
      state.view= [];
      state.loading = true;
      state.error = "";
    },
    [fetchInstitute.fulfilled] : (state, action) => {
      state.view= action.payload;
      state.loading = false;
      state.error = "";
    },
    [fetchInstitute.rejected] : (_, action) => {
      errorState(action.payload)
    },
    [postInstitute.rejected] : (_, action) => {
      errorState(action.payload)
    },
    [putInstitute.fulfilled] : (state) => {
      state.error = "";
    },
    [putInstitute.rejected] : (_, action) => {
      errorState(action.payload)
    },
    [deleteInstitute.rejected] : (_, action) => {
      errorState(action.payload)
    },
    [deleteInstitute.fulfilled] : (state) => {
      state.view= {};
      state.loading = false;
      state.error = "";
    },
    [fetchMainCourses.pending] : (state) => {
      state.mainCourses= [];
      state.loading = true;
      state.error = "";
    },
    [fetchMainCourses.fulfilled] : (state, action) => {
      state.mainCourses= action.payload;
      state.loading = false;
      state.error = "";
    },
    [fetchMainCourses.rejected] : (_, action) => {
      errorState(action.payload)
    },
    [fetchMainInstitutes.pending] : (state) => {
      state.mainInstitutes= [];
      state.loading = true;
      state.error = "";
    },
    [fetchMainInstitutes.fulfilled] : (state, action) => {
      state.mainInstitutes= action.payload;
      state.loading = false;
      state.error = "";
    },
    [fetchMainInstitutes.rejected] : (_, action) => {
      errorState(action.payload)
    },
    [fetchCourses.pending] : (state) => {
      state.courses= [];
      state.loading = true;
      state.error = "";
    },
    [fetchCourses.fulfilled] : (state, action) => {
      state.courses= action.payload;
      state.loading = false;
      state.error = "";
    },
    [fetchCourses.rejected] : (_, action) => {
      errorState(action.payload)
    },
    [fetchReviews.pending] : (state) => {
      state.reviews= {};
      state.loading = true;
      state.error = "";
    },
    [fetchReviews.fulfilled] : (state, action) => {
      state.reviews= action.payload;
      state.loading = false;
      state.error = "";
    },
    [fetchReviews.rejected] : (_, action) => {
      errorState(action.payload)
    },
  }
})

export default instituteSlice.reducer