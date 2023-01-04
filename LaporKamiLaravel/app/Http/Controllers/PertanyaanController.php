<?php

namespace App\Http\Controllers;

use App\Models\Questions;
use Illuminate\Http\Request;

class PertanyaanController extends Controller
{
    public function listPertanyaan(Request $request)
    {
        return response()->json(Questions::all(), 200);
    }
}
