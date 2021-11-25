import { combineReducers } from 'redux';
import { all } from 'redux-saga/effects';
import loading from './loading';
import searchMnt, {
  searchMntSaga,
  deleteSearchTextSaga,
  changeValidateSaga,
  transferSearchTextSaga,
} from './admin/searchMnt';
import foodMnt, { foodInfoListSaga } from './admin/foodMnt';

const rootReducer = combineReducers({
  loading,
  searchMnt,
  foodMnt,
});

export function* rootSaga() {
  yield all([
    searchMntSaga(),
    deleteSearchTextSaga(),
    changeValidateSaga(),
    transferSearchTextSaga(),
    foodInfoListSaga(),
  ]);
}

export default rootReducer;
