import { createAction, handleActions } from 'redux-actions';
import * as memberAPI from '../../lib/api/member';
import createRequestSaga, {
  createRequestActionTypes,
} from '../../lib/createRequestSaga';
import { takeLatest } from 'redux-saga/effects';
import produce from "immer";

const CHANGE_FIELD ='member/CHANGE_FIELD';
const INITIALIZE_FORM ='member/INITIALIZE_FORM';


const [FIND_ID, FIND_ID_SUCCESS, FIND_ID_FAILURE] =
    createRequestActionTypes('/member/findId');
const [FIND_PASSWORD, FIND_PASSWORD_SUCCESS, FIND_PASSWORD_FAILURE] =
    createRequestActionTypes('/member/findPassword');
const [REGISTER, REGISTER_SUCCESS, REGISTER_FAILURE] =
    createRequestActionTypes('/member/register');
const [CHECK_ID, CHECK_ID_SUCCESS, CHECK_ID_FAILURE] =
    createRequestActionTypes('/member/checkId');
export const chengeField = createAction(
    CHANGE_FIELD,
    ({ form, key, value}) => ({
        form,
        key, // 컴포넌트
        value //실제 바꾸려는 값
    }),
);

export const initializeForm = createAction(INITIALIZE_FORM, form => form);
export const findId = createAction(FIND_ID, ({ userName, phone, email }) =>({
    userName,
    phone,
    email
}));
export const findPassword = createAction(FIND_PASSWORD, ({ userId, phone, email }) =>({
    userId,
    phone,
    email
}));
export const registerData = createAction(REGISTER,
    ({ userId,userName, userPassword, userPasswordConfirm,email,phone,gender,age }) =>({
        userId,
        userName,
        userPassword,
        userPasswordConfirm,
        email,
        phone,
        gender,
        age
    }));
export const checkId = createAction(CHECK_ID, ({ userId }) =>({
    userId
}));
const initialState = {
    register: {
        userId: '',
        userName:'',
        userPassword: '',
        userPasswordConfirm: '',
        email: '',
        phone: '',
        gender: '',
        age: '',
    },
    findId: {
        email: '',
        userName: '',
        phone: '',
    },
    findPassword: {
        email: '',
        userId: '',
        phone: '',
    },
    resultMsg: null,
    error: null
};

//사가 생성
const findIdSaga = createRequestSaga(
  FIND_ID,
  memberAPI.getFindId,
);

const findPasswordSaga = createRequestSaga(
    FIND_PASSWORD,
    memberAPI.getFindPassword,
);
const registerSaga = createRequestSaga(
    REGISTER,
    memberAPI.register,
);
const checkIdSaga = createRequestSaga(
    CHECK_ID,
    memberAPI.getCheckId,
);
export function* memberSaga() {
  yield takeLatest(FIND_ID, findIdSaga);
  yield takeLatest(FIND_PASSWORD, findPasswordSaga);
  yield takeLatest(REGISTER, registerSaga);
  yield takeLatest(CHECK_ID, checkIdSaga);
}


const member = handleActions(
{
        [CHANGE_FIELD]: (state, { payload: { form, key, value} }) =>
            produce(state,draft => {
                draft[form][key] = value; //state.form.userName
          }),
        [INITIALIZE_FORM]: (state, { payload: form }) => ({
            ...state,
            [form] : initialState[form],
            error :null,
        }),
        [FIND_ID_SUCCESS]: (state, { payload: resultMsg }) => ({
            ...state,
            error :null ,
            resultMsg
        }),
        [FIND_ID_FAILURE]: (state, { payload: error }) => ({
            ...state,
            error : error,
        }),
        [FIND_PASSWORD_SUCCESS]: (state, { payload: resultMsg }) => ({
            ...state,
            error :null ,
            resultMsg
        }),
        [FIND_PASSWORD_FAILURE]: (state, { payload: error }) => ({
            ...state,
            error : error,
        }),
        [REGISTER_SUCCESS]: (state, { payload: resultMsg }) => ({
            ...state,
            error :null ,
            resultMsg
        }),
        [REGISTER_FAILURE]: (state, { payload: error }) => ({
            ...state,
            error : error,
        }),
        [CHECK_ID_SUCCESS]: (state, { payload: resultMsg }) => ({
            ...state,
            error :null ,
            resultMsg
        }),
        [CHECK_ID_FAILURE]: (state, { payload: error }) => ({
            ...state,
            error : error,
        }),
  },
  initialState,
);

export default member;
