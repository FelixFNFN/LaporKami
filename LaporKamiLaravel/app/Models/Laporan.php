<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class Laporan extends Model
{
    use HasFactory;
    protected $table = "laporan";
    protected $primaryKey = "id";
    protected $fillable = ["laporan","detail","id_pelapor"];
    public $timestamps = false;

    // public function users()
    // {
    //     return $this->belongsTo(Users::class, 'id','id');
    // }
}
