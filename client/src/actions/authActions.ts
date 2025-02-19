// src/actions/authActions.ts
import { AppDispatch } from '../store';
import { loginSuccess, loginFailure, logout } from '../reducers/authReducer';

export const login = (username: string, password: string) => async (dispatch: AppDispatch) => {
  try {
    const response = await fakeApiLogin(username, password);
    const { token } = response.data;

    localStorage.setItem('username', username);
    localStorage.setItem('token', token);

    dispatch(loginSuccess({ username, token }));
  } catch (error) {
    if (error instanceof Error) {
      dispatch(loginFailure(error.message));
    } else {
      dispatch(loginFailure('An unknown error occurred.'));
    }
  }
};

export const logoutAction = () => (dispatch: AppDispatch) => {
  localStorage.removeItem('username');
  localStorage.removeItem('token');
  dispatch(logout());
};

const fakeApiLogin = (username: string, password: string): Promise<{ data: { token: string } }> => {
  return new Promise((resolve, reject) => {
    setTimeout(() => {
      if (username === 'test' && password === '11') {
        resolve({ data: { token: 'fake-jwt-token' } });
      } else {
        reject(new Error('Invalid credentials'));
      }
    }, 1000);
  });
};
