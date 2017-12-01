import {Component, OnDestroy, OnInit} from '@angular/core';
import {StockService} from "../stock.service";
import {Stock} from "../Stock";
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {ActivatedRoute, Router} from "@angular/router";

@Component({
  selector: 'app-stock-create',
  templateUrl: './stock-create.component.html',
  styleUrls: ['./stock-create.component.css'],
  providers: [StockService]
})
export class StockCreateComponent implements OnInit, OnDestroy {

  id: number;
  stock: Stock;

  stockForm: FormGroup;
  private sub: any;

  constructor(private route: ActivatedRoute,
              private router: Router,
              private stockService: StockService) { }

  ngOnInit() {
    this.sub = this.route.params.subscribe(params => {
      this.id = params['id'];
    });

    this.stockForm = new FormGroup({
      symbl: new FormControl('', Validators.required),
      own: new FormControl('', Validators.required),
      entryPrice: new FormControl('', Validators.required),
      up: new FormControl('', Validators.required),
      target: new FormControl('', Validators.required),
      time: new FormControl('', Validators.required)
    });

    if (this.id) {
      this.stockService.findById(this.id).subscribe(
        stock => {
          this.id = stock.id;
          this.stockForm.patchValue({
            symbl: stock.symbl,
            own: stock.own,
            entryPrice: stock.entryPrice,
            up: stock.up,
            target: stock.target,
            time: stock.time,

          });
        },error => {
          console.log(error);
        }
      );

    }
  }

  ngOnDestroy(): void {
    this.sub.unsubscribe();
  }

  onSubmit() {
    if (this.stockForm.valid) {
      if (this.id) {
        let stock: Stock = new Stock(this.id,
          this.stockForm.controls['symbl'].value,
          this.stockForm.controls['own'].value,
          this.stockForm.controls['entryPrice'].value,
          this.stockForm.controls['up'].value,
          this.stockForm.controls['target'].value,
          this.stockForm.controls['time'].value);
        this.stockService.updateStock(stock).subscribe();
      } else {
        let stock: Stock = new Stock(null,
          this.stockForm.controls['symbl'].value,
          this.stockForm.controls['own'].value,
          this.stockForm.controls['entryPrice'].value,
          this.stockForm.controls['up'].value,
          this.stockForm.controls['target'].value,
          this.stockForm.controls['time'].value);
        this.stockService.saveStock(stock).subscribe();

      }

      this.stockForm.reset();
      this.redirectStockPage();

    }
  }

  redirectStockPage() {
    this.router.navigate(['/stock']);

  }

}
