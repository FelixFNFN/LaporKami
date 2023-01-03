<?php

use App\Http\Controllers\AktifitasController;
use App\Http\Controllers\LaporanController;
use App\Http\Controllers\PertanyaanController;
use App\Http\Controllers\UserController;
use Illuminate\Http\Request;
use Illuminate\Support\Facades\Route;

/*
|--------------------------------------------------------------------------
| API Routes
|--------------------------------------------------------------------------
|
| Here is where you can register API routes for your application. These
| routes are loaded by the RouteServiceProvider within a group which
| is assigned the "api" middleware group. Enjoy building your API!
|
*/

Route::middleware('auth:sanctum')->get('/user', function (Request $request) {
    return $request->user();
});


Route::get('/user/insert', [UserController::class, "insertUser"]);
Route::post('/user/insert', [UserController::class, "RegisterUser"]);
Route::get('/user/login', [UserController::class, "insertUser"]);
Route::post('/user/login', [UserController::class, "LoginUser"]);
Route::get('/aktifitas', [AktifitasController::class, "listAktifitas"]);
Route::get('/laporan', [LaporanController::class, "listLaporan"]);
Route::post('/laporan/insert', [LaporanController::class, "LoginUser"]);
Route::get('/pertanyaan', [PertanyaanController::class, "listPertanyaan"]);
Route::post('/pertanyaan/insert', [PertanyaanController::class, "LoginUser"]);
Route::prefix("admin")->group(function(){
    Route::get('/aktifitas/insert', [AktifitasController::class, "LoginUser"]);
    Route::post('/aktifitas/insert', [AktifitasController::class, "LoginUser"]);
});
// Route::post('/mahasiswa/update', [UserController::class, "updateMhs"]);
// Route::post('/mahasiswa/delete', [UserController::class, "deleteMhs"]);
