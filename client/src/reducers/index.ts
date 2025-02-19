// src/reducers/index.ts

import { combineReducers } from 'redux';
import authReducer from './authReducer';

// 여러 리듀서를 하나로 결합.
const rootReducer = combineReducers({
  auth: authReducer, // authReducer는 'auth' 상태 조각을 관리.
});

// 루트 리듀서의 반환 타입을 정의하여, 상태 트리의 타입을 유추.
export type RootState = ReturnType<typeof rootReducer>;

// 결합된 루트 리듀서를 내보낸다.
// 결합된 루트 리듀서를 내보내어, Redux 스토어 생성 시 사용
export default rootReducer;
