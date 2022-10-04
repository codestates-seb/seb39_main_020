import { configureStore } from '@reduxjs/toolkit'
import logger from 'redux-logger'
import reducer from '../reducers/rootReducer'

export const store = configureStore({
  reducer,
  middleware: (getDefaultMiddleware) => {
    return getDefaultMiddleware({
      serializableCheck: {ignoredActionPaths: ['meta.arg','payload.successFn']}
    }).concat(logger)
  },
})