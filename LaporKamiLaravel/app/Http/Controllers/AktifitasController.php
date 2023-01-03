<?php

namespace App\Http\Controllers;

use App\Models\Aktivitas;
use Illuminate\Http\Request;

class AktifitasController extends Controller
{
    public function listAktifitas(Request $request)
    {
        return response()->json(Aktivitas::all(), 200);
    }

    // public function FunctionName(Type $var = null)
    // {
    //     # code...
    // }
}
