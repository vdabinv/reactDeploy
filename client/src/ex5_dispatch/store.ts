// src/ex5_dispatch/store.ts
import { configureStore } from '@reduxjs/toolkit';
import counterReducer from './counterSlice';
import { useDispatch } from 'react-redux';

// 로그 미들웨어
const loggerMiddleware = (store: any) => (next: any) => (action: any) => {
  console.log('첫번째 미들웨어');
  console.log('store', store);
  console.log('action', action);
  next(action);
};

const store = configureStore({
  reducer: {
    counter: counterReducer,
  },
  middleware: (getDefaultMiddleware) =>
    getDefaultMiddleware({
      serializableCheck: false,
    }).concat(loggerMiddleware),
});

export type AppDispatch = typeof store.dispatch;
export type RootState = ReturnType<typeof store.getState>;
export const useAppDispatch = () => useDispatch<AppDispatch>();

export default store;
