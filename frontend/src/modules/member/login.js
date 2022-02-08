import { createAction, handleActions } from 'redux-actions';
import * as memberAPI from '../../lib/api/member';
import createRequestSaga, {
  createRequestActionTypes,
} from '../../lib/createRequestSaga';
import { takeLatest } from 'redux-saga/effects';
import produce from "immer";

const CHANGE_FIELD ='login/CHANGE_FIELD';
//const INITIALIZE_LOGIN_FORM ='login/INITIALIZE_FORM';

const [LOGIN, LOGIN_SUCCESS, LOGIN_FAILURE] =
    createRequestActionTypes('/member/login');

export const chengeField = createAction(
    CHANGE_FIELD,
    ({ key, value}) => ({
        key, // 컴포넌트
        value //실제 바꾸려는 값
    }),
);

//export const initializeLoginForm = createAction(INITIALIZE_LOGIN_FORM, form => form);
export const login = createAction(LOGIN,
    ({ userId,userPassword}) =>({
        userId,
        userPassword,
}));

const initialLoginState = {
    userId: '',
    userPassword: '',
    resultMsg: null,
    error: null
};

//사가 생성
const loginDataSaga = createRequestSaga(
  LOGIN,
  memberAPI.login,
);
export function* loginSaga() {
  yield takeLatest(LOGIN, loginDataSaga);
}

const memberLogin = handleActions(
{
        [CHANGE_FIELD]: (state, { payload: { key, value } }) =>({
        ...state,
        [key]: value,
          }),
/*        [INITIALIZE_FORM]: (state, { payload: form }) => ({
            ...state,
            [form] : initialLoginState[form],
            error :null,
        }),*/
        [LOGIN_SUCCESS]: (state, { payload: resultMsg }) => ({
            ...state,
            error :null ,
            resultMsg
        }),
        [LOGIN_FAILURE]: (state, { payload: error }) => ({
            ...state,
            error : error,
        }),
  },
    initialLoginState,
);

export default memberLogin;
