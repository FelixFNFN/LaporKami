<?php

namespace App\Http\Controllers;

use App\Models\Laporan;
use Illuminate\Http\Request;

class LaporanController extends Controller
{
    public function listLaporan(Request $request)
    {
        return response()->json(Laporan::all(), 200);
    }
}
